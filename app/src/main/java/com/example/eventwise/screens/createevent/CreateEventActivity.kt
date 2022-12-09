package com.example.eventwise.screens.createevent

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
import com.example.eventwise.databinding.ActivityCreateEventBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class CreateEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0

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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        createEventViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.createEventActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.show()
                }
            }
        }

        binding.createEventActivityChooseDateTimeButton.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(this@CreateEventActivity, this@CreateEventActivity,
                    year, month, day)
            datePickerDialog.show()
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

    override fun onStart() {
        super.onStart()
        if (groupId == Long.MIN_VALUE){
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
        val timePickerDialog = TimePickerDialog(this@CreateEventActivity, this@CreateEventActivity, hour, minute, true)
        timePickerDialog.show()
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        this.hour = hourOfDay
        this.minute = minute
        val dateTimeText = "${this.hour}:${this.minute} ${this.day}-${this.month}-${this.year}"
        binding.createEventActivityEditTextEventDateTime.text = dateTimeText
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
