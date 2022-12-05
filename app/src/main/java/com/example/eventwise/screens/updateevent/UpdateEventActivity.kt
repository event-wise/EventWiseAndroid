package com.example.eventwise.screens.updateevent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityUpdateEventBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class UpdateEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0


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

        binding.updateEventActivityChooseDateTimeButton.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(this@UpdateEventActivity, this@UpdateEventActivity,
                year, month, day)
            datePickerDialog.show()
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

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this@UpdateEventActivity, this@UpdateEventActivity, hour, minute,
            DateFormat.is24HourFormat(this))
        timePickerDialog.show()
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val dateTimeText = "$hour:$minute $day/$month/$year"
        binding.updateEventActivityEditTextEventDateTime.text = dateTimeText
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
