package com.example.eventwise.screens.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivitySignUpBinding
import com.example.eventwise.screens.login.LoginActivityViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private val signUpActivityViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.lifecycleOwner = this

        binding.viewModel = signUpActivityViewModel

        binding.signUpActivityCancelButton.setOnClickListener {
            finish()
        }
    }
}
