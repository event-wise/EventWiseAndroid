package com.example.eventwise.screens.groupdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityGroupDetailBinding
import com.example.eventwise.screens.createevent.CreateEventActivity

class GroupDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupDetailBinding

    private val groupId: Long
        get() = intent.getLongExtra(KEY_GROUP_ID, Long.MIN_VALUE)

    private val groupDetailViewModel: GroupDetailsViewModel by viewModels {
        GroupDetailsViewModelFactory(groupId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_detail)

        binding.lifecycleOwner = this

        binding.viewModel = groupDetailViewModel

        binding.groupDetailActivityActiveEventRecyclerView.adapter = ActiveEventsRecyclerViewAdapter()

        binding.groupDetailActivityMemberRecyclerView.adapter = MemberListRecyclerViewAdapter()

        binding.groupDetailActivityLogRecyclerView.adapter = LogListRecyclerViewAdapter()

        binding.groupDetailActivityCreateEventButton.setOnClickListener {
            CreateEventActivity.newInstance(applicationContext, groupId)
        }
    }

    companion object {
        private const val KEY_GROUP_ID = "group_id"

        val newInstance = { context: Context, eventId: Long ->
            val intent = Intent(context, GroupDetailActivity::class.java)
            intent.putExtra(KEY_GROUP_ID, eventId)
            context.startActivity(intent)
        }
    }
}
