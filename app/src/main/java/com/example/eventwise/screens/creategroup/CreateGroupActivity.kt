package com.example.eventwise.screens.creategroup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityCreateGroupBinding

class CreateGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateGroupBinding

    private val createGroupViewModel: CreateGroupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_group)

        binding.lifecycleOwner = this

        binding.viewModel = createGroupViewModel
    }
}
