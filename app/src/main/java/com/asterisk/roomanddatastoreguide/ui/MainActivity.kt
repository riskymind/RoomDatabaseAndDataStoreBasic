package com.asterisk.roomanddatastoreguide.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.asterisk.roomanddatastoreguide.R
import com.asterisk.roomanddatastoreguide.UserViewModel
import com.asterisk.roomanddatastoreguide.databinding.ActivityMainBinding
import com.asterisk.roomanddatastoreguide.models.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveButtonSetup()

        handleClick()

        observeResponses()
    }

    private fun observeResponses() {
        viewModel.insertResponse.observe(this) {
            Toast.makeText(this, "Created!!!", Toast.LENGTH_LONG).show()

            Intent(this, DetailActivity::class.java).also {
                startActivity(it)
            }


        }
    }

    private fun handleClick() {
        // On click of button save
        binding.btnSave.setOnClickListener {

            // Get user details
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString()
            val number = binding.etNumber.text.toString()

            val user = User(id = 1, name = name, age = age, number = number)

            // Save user record
            viewModel.insertUser(user)

        }
    }

    private fun saveButtonSetup() {
        // Make Button UnClickable for the first time
        notClickable()

        // Make the button clickable after detecting changes in input field
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val nameEt = binding.etName.text.toString()
                val ageEt = binding.etAge.text.toString()
                val numberEt = binding.etNumber.text.toString()

                if (nameEt.isEmpty() || ageEt.isEmpty() || numberEt.isEmpty()) {
                    notClickable()
                } else {
                    clickable()
                }


            }

            override fun afterTextChanged(p0: Editable?) {}

        }


        binding.etName.addTextChangedListener(watcher)
        binding.etAge.addTextChangedListener(watcher)
        binding.etNumber.addTextChangedListener(watcher)

    }

    private fun notClickable() {
        binding.btnSave.isEnabled = false
        binding.btnSave.background = ContextCompat.getDrawable(
            this, R.drawable.btn_opaque
        )
    }

    private fun clickable() {
        binding.btnSave.isEnabled = true
        binding.btnSave.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.btn_round
        )
    }
}