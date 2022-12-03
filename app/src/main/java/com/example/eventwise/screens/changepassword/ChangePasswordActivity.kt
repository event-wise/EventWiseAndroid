package com.example.eventwise.screens.changepassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)

        binding.lifecycleOwner = this

        binding.viewModel = ChangePasswordViewModel()


    }
}
