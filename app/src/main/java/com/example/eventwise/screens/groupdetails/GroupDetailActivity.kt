package com.example.eventwise.screens.groupdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityGroupDetailBinding

class GroupDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_detail)

        binding.lifecycleOwner = this

        binding.viewModel = GroupDetailsViewModel()

        binding.groupDetailActivityActiveEventRecyclerView.adapter = ActiveEventsRecyclerViewAdapter()

        binding.groupDetailActivityMemberRecyclerView.adapter = MemberListRecyclerViewAdapter()

        binding.groupDetailActivityLogRecyclerView.adapter = LogListRecyclerViewAdapter()
    }
}
