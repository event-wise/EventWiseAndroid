package com.example.eventwise.updategroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityUpdateGroupBinding

class UpdateGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_group)

        binding.lifecycleOwner = this

        binding.viewModel = UpdateGroupViewModel()
    }
}
