package com.example.eventwise.eventdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityEventDetailBinding

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)

        binding.lifecycleOwner = this

        binding.viewModel = EventDetailViewModel()

        binding.eventDetailActivityMemberRecyclerView.adapter = MemberListRecyclerViewAdapter()

    }
}
