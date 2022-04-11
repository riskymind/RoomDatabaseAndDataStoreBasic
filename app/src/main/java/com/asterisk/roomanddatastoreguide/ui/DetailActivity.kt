package com.asterisk.roomanddatastoreguide.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.asterisk.roomanddatastoreguide.viewmodels.UserViewModel
import com.asterisk.roomanddatastoreguide.databinding.ActivityDetailBinding
import com.asterisk.roomanddatastoreguide.viewmodels.DatastoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<UserViewModel>()
    private val datastoreViewModel by viewModels<DatastoreViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserDetail()

        handleDeleteClick()
    }

    private fun handleDeleteClick() {
        // On click delete button
        binding.btnClearRecord.setOnClickListener {
            // Clear the db records
            viewModel.deleteRecords()

            datastoreViewModel.setSaveKey(false)

            // Navigate to form screen
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun getUserDetail() {
        this.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUsers()
                viewModel.users.collect { users ->
                    for (user in users) {
                        binding.apply {
                            txtName.text = user.name
                            txtAge.text = user.age
                            txtNumber.text = user.number
                        }
                    }
                }
            }
        }
    }
}