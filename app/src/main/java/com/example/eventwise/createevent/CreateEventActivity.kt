package com.example.eventwise.createevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.eventwise.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

class CreateEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)

        ArrayAdapter.createFromResource(
            this,
            R.array.event_types,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            val dropdown = findViewById<MaterialAutoCompleteTextView>(R.id.createEventActivityEditTextEventType)
            dropdown.setAdapter(adapter)

            val dropdownLayout = findViewById<TextInputLayout>(R.id.createEventActivityEditTextEventTypeLayout)
            dropdownLayout.setOnClickListener {
                dropdown.showDropDown()
            }
            dropdown.setOnClickListener {
                dropdown.showDropDown()
            }
        }
    }
}
