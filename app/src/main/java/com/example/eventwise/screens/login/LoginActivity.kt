
package com.example.eventwise.screens.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityLoginBinding
import com.example.eventwise.screens.home.HomeActivity
import com.example.eventwise.screens.signup.SignUpActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginActivityViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.lifecycleOwner = this

        binding.viewModel = loginActivityViewModel

        loginActivityViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.loginActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.setTextMaxLines(10)
                    it.show()
                }
            }
        }

        loginActivityViewModel.success.observe(this) {
            if (it == true){
                finish()
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }

        binding.loginActivitySignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.loginActivityLoginButton.setOnClickListener {
            loginActivityViewModel.login()
        }
    }
}
