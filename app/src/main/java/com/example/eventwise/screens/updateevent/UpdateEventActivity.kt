package com.example.eventwise.screens.updateevent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityUpdateEventBinding
import com.google.android.material.snackbar.Snackbar

class UpdateEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateEventBinding

    private val eventId: Long
        get() = intent.getLongExtra(KEY_EVENT_ID, Long.MIN_VALUE)

    private val groupId: Long
        get() = intent.getLongExtra(KEY_GROUP_ID, Long.MIN_VALUE)

    private val updateEventViewModel: UpdateEventViewModel by viewModels{
        UpdateEventViewModelFactory(eventId, groupId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_event)

        binding.lifecycleOwner = this

        binding.viewModel = updateEventViewModel

        ArrayAdapter.createFromResource(
            this,
            R.array.event_types,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.updateEventActivityEditTextEventType.setAdapter(adapter)

            binding.updateEventActivityEditTextEventTypeLayout.setOnClickListener {
                binding.updateEventActivityEditTextEventType.showDropDown()
            }
            binding.updateEventActivityEditTextEventType.setOnClickListener {
                binding.updateEventActivityEditTextEventType.showDropDown()
            }
        }
        updateEventViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.updateEventActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.show()
                }
            }
        }

        updateEventViewModel.success.observe(this) {
            if (it == true){
                finish()
            }
        }

        binding.updateEventActivityCancelButton.setOnClickListener {
            finish()
        }

        binding.updateEventActivitySaveButton.setOnClickListener {
            updateEventViewModel.updateEvent()
        }
    }

    companion object {
        private const val KEY_EVENT_ID = "event_id_update"
        private const val KEY_GROUP_ID = "group_id_update"

        val newInstance = { context: Context, eventId: Long, groupId: Long ->
            val intent = Intent(context, UpdateEventActivity::class.java)
            intent.putExtra(KEY_EVENT_ID, eventId)
            intent.putExtra(KEY_GROUP_ID, groupId)
            context.startActivity(intent)
        }
    }
}
