package com.example.eventwise.updateevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityUpdateEventBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class UpdateEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_event)

        binding.lifecycleOwner = this

        binding.viewModel = UpdateEventViewModel()

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
    }
}
