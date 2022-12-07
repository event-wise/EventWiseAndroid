package com.example.eventwise.screens.signup

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private val signUpActivityViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.lifecycleOwner = this

        binding.viewModel = signUpActivityViewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        signUpActivityViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.signUpActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.show()
                }
            }
        }

        signUpActivityViewModel.success.observe(this) {
            if (it == true){
                finish()
            }
        }

        binding.signUpActivityCancelButton.setOnClickListener {
            finish()
        }

        binding.signUpActivitySignUpButton.setOnClickListener {
            signUpActivityViewModel.signup()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
