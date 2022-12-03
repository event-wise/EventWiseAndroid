
package com.example.eventwise.screens.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityLoginBinding
import com.example.eventwise.screens.home.HomeActivity
import com.example.eventwise.screens.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginActivityViewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.lifecycleOwner = this

        binding.viewModel = loginActivityViewModel

        binding.loginActivitySignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.loginActivityLoginButton.setOnClickListener {
            finish()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
