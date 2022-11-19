package com.example.eventwise.updateevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import com.example.eventwise.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class UpdateEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_event)

        ArrayAdapter.createFromResource(
            this,
            R.array.event_types,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            val dropdown = findViewById<MaterialAutoCompleteTextView>(R.id.updateEventActivityEditTextEventType)
            dropdown.setAdapter(adapter)

            val dropdownLayout = findViewById<TextInputLayout>(R.id.updateEventActivityEditTextEventTypeLayout)
            dropdownLayout.setOnClickListener {
                dropdown.showDropDown()
            }
            dropdown.setOnClickListener {
                dropdown.showDropDown()
            }
        }
    }
}
