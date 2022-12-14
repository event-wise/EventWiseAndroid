package com.example.eventwise.screens.eventdetail

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityEventDetailBinding
import com.example.eventwise.screens.updateevent.UpdateEventActivity
import com.google.android.material.snackbar.Snackbar

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

        eventDetailViewModel.eventName.observe(this) {
            this.title = it
        }

        eventDetailViewModel.isDeleted.observe(this) {
            if (it == true){
                finish()
            }
        }

        eventDetailViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.eventDetailActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.setTextMaxLines(10)
                    it.show()
                }
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        if (eventId == Long.MIN_VALUE){
            finish()
        }
        eventDetailViewModel.retrieveEventDetail()
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

    companion object {
        private const val KEY_EVENT_ID = "event_id"

        val newInstance = { context: Context, eventId: Long ->
            val intent = Intent(context, EventDetailActivity::class.java)
            intent.putExtra(KEY_EVENT_ID, eventId)
            context.startActivity(intent)
        }
    }
}
