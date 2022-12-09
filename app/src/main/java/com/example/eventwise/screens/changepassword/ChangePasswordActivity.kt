package com.example.eventwise.screens.changepassword

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityChangePasswordBinding
import com.google.android.material.snackbar.Snackbar

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)

        binding.lifecycleOwner = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewModel = changePasswordViewModel

        changePasswordViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.changePasswordActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.setTextMaxLines(10)
                    it.show()
                }
            }
        }

        changePasswordViewModel.success.observe(this) {
            if (it == true){
                finish()
            }
        }

        binding.changePasswordActivitySaveButton.setOnClickListener {
            changePasswordViewModel.changePassword()
        }

        binding.changePasswordActivityCancelButton.setOnClickListener {
            finish()
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
