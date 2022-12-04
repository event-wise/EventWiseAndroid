package com.example.eventwise.screens.eventdetail

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityEventDetailBinding
import com.example.eventwise.screens.updateevent.UpdateEventActivity

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding

    private val eventId: Long
        get() = intent.getLongExtra(KEY_EVENT_ID, Long.MIN_VALUE)

    private val eventDetailViewModel: EventDetailViewModel by viewModels {
        EventDetailViewModelFactory(eventId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)

        binding.lifecycleOwner = this

        binding.viewModel = eventDetailViewModel

        binding.eventDetailActivityMemberRecyclerView.adapter = MemberListRecyclerViewAdapter()

        binding.eventDetailActivityRejectButton.setOnClickListener {
            eventDetailViewModel.rejectEvent()
        }

        binding.eventDetailActivityAcceptButton.setOnClickListener {
            eventDetailViewModel.acceptEvent()
        }

        binding.eventDetailActivityUpdateEventButton.setOnClickListener {
            UpdateEventActivity.newInstance(this, eventId, eventDetailViewModel.eventDetail.value?.groupId ?: 0)
        }

        eventDetailViewModel.eventOwner.observe(this) {
            binding.eventDetailActivityUpdateEventButton.visibility = if (it){
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

    override fun onStart() {
        super.onStart()
        eventDetailViewModel.retrieveEventDetail()
    }

    companion object {
        private const val KEY_EVENT_ID = "event_id"
        private const val KEY_GROUP_ID = "group_id"

        val newInstance = { context: Context, eventId: Long ->
            val intent = Intent(context, EventDetailActivity::class.java)
            intent.putExtra(KEY_EVENT_ID, eventId)
            context.startActivity(intent)
        }
    }
}
