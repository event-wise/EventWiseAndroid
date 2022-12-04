package com.example.eventwise.screens.createevent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityCreateEventBinding
import com.example.eventwise.screens.groupdetails.GroupDetailActivity
import com.google.android.material.snackbar.Snackbar

class CreateEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateEventBinding

    private val groupId: Long
        get() = intent.getLongExtra(KEY_GROUP_ID_CREATE, Long.MIN_VALUE)

    private val createEventViewModel: CreateEventViewModel by viewModels {
        CreateEventViewModelFactory(groupId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_event)

        binding.lifecycleOwner = this

        binding.viewModel = createEventViewModel

        createEventViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.createEventActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.show()
                }
            }
        }

        createEventViewModel.success.observe(this) {
            if (it == true){
                finish()
            }
        }

        binding.createEventActivitySaveButton.setOnClickListener {
            createEventViewModel.createEvent()
        }

        binding.createEventActivityCancelButton.setOnClickListener {
            finish()
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.event_types,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.createEventActivityEditTextEventType.setAdapter(adapter)

            binding.createEventActivityEditTextEventTypeLayout.setOnClickListener {
                binding.createEventActivityEditTextEventType.showDropDown()
            }
            binding.createEventActivityEditTextEventType.setOnClickListener {
                binding.createEventActivityEditTextEventType.showDropDown()
            }
        }
    }

    companion object {
        private const val KEY_GROUP_ID_CREATE = "group_id_create"

        val newInstance = { context: Context, groupId: Long ->
            val intent = Intent(context, CreateEventActivity::class.java)
            intent.putExtra(KEY_GROUP_ID_CREATE, groupId)
            context.startActivity(intent)
        }
    }
}
