package com.asterisk.roomanddatastoreguide.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.asterisk.roomanddatastoreguide.R
import com.asterisk.roomanddatastoreguide.UserViewModel
import com.asterisk.roomanddatastoreguide.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserDetail()
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