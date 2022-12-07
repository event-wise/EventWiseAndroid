package com.example.eventwise.screens.creategroup

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityCreateGroupBinding
import com.google.android.material.snackbar.Snackbar

class CreateGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateGroupBinding

    private val createGroupViewModel: CreateGroupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_group)

        binding.lifecycleOwner = this

        binding.viewModel = createGroupViewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        createGroupViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.createGroupActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.show()
                }
            }
        }

        createGroupViewModel.success.observe(this) {
            if (it == true){
                finish()
            }
        }

        binding.createGroupActivitySaveButton.setOnClickListener {
            createGroupViewModel.createGroup()
        }

        binding.createGroupActivityCancelButton.setOnClickListener {
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
