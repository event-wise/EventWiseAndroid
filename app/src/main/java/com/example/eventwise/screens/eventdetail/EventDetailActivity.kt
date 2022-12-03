package com.example.eventwise.screens.eventdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityEventDetailBinding

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding

    private val eventId: Long
        get() = intent.getLongExtra(KEY_EVENT_ID, Long.MIN_VALUE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)

        binding.lifecycleOwner = this

        binding.viewModel = EventDetailViewModel()

        binding.eventDetailActivityMemberRecyclerView.adapter = MemberListRecyclerViewAdapter()

    }

    companion object {
        private const val KEY_EVENT_ID = "event_id"

        val newInstance = { context: Context, eventId: Long ->
            val intent = Intent(context, EventDetailActivity::class.java)
            intent.putExtra(KEY_EVENT_ID, eventId)
            context.startActivity(intent)
        }
    }
}
