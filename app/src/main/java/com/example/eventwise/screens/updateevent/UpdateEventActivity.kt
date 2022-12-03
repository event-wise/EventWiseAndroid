package com.example.eventwise.screens.updateevent

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityUpdateEventBinding
import com.example.eventwise.screens.eventdetail.EventDetailActivity

class UpdateEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateEventBinding

    private val eventId: Long
        get() = intent.getLongExtra(UpdateEventActivity.KEY_EVENT_ID, Long.MIN_VALUE)

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

    companion object {
        private const val KEY_EVENT_ID = "event_id_update"

        val newInstance = { context: Context, eventId: Long ->
            val intent = Intent(context, UpdateEventActivity::class.java)
            intent.putExtra(KEY_EVENT_ID, eventId)
            context.startActivity(intent)
        }
    }
}
