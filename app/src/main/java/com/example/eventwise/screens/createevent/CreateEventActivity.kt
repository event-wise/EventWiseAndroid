package com.example.eventwise.screens.createevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityCreateEventBinding

class CreateEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_event)

        binding.lifecycleOwner = this

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
}
