package com.example.eventwise.screens.updateevent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityUpdateEventBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
                    it.setTextMaxLines(10)
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

        binding.updateEventActivityDeleteEventButton.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setMessage(resources.getString(R.string.sure_delete_event))
                .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
                    dialog.dismiss()
                }
                .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                    updateEventViewModel.deleteEvent()
                }
                .show()
        }

        updateEventViewModel.eventOwner.observe(this) {
            if (it == false){
                updateEventViewModel.errorMessage.value = "You can not edit this event!"
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (groupId == Long.MIN_VALUE || eventId == Long.MIN_VALUE){
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

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar: Calendar = Calendar.getInstance()
        this.year = year
        this.month = month + 1
        this.day = dayOfMonth
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this@UpdateEventActivity, this@UpdateEventActivity, hour, minute, true)
        timePickerDialog.show()
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        this.hour = hourOfDay
        this.minute = minute
        val dateTimeText = "${this.hour}:${this.minute} ${this.day}-${this.month}-${this.year}"
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
