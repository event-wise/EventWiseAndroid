package com.example.eventwise.screens.groupdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityGroupDetailBinding
import com.example.eventwise.screens.eventdetail.EventDetailActivity

class GroupDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupDetailBinding

    private val groupId: Long
        get() = intent.getLongExtra(GroupDetailActivity.KEY_GROUP_ID, Long.MIN_VALUE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_detail)

        binding.lifecycleOwner = this

        binding.viewModel = GroupDetailsViewModel()

        binding.groupDetailActivityActiveEventRecyclerView.adapter = ActiveEventsRecyclerViewAdapter()

        binding.groupDetailActivityMemberRecyclerView.adapter = MemberListRecyclerViewAdapter()

        binding.groupDetailActivityLogRecyclerView.adapter = LogListRecyclerViewAdapter()
    }

    companion object {
        private const val KEY_GROUP_ID = "event_id"

        val newInstance = { context: Context, eventId: Long ->
            val intent = Intent(context, GroupDetailActivity::class.java)
            intent.putExtra(KEY_GROUP_ID, eventId)
            context.startActivity(intent)
        }
    }
}
