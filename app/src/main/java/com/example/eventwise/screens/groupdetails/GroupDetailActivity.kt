package com.example.eventwise.screens.groupdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityGroupDetailBinding
import com.example.eventwise.screens.createevent.CreateEventActivity
import com.example.eventwise.screens.updategroup.UpdateGroupActivity
import com.example.eventwise.screens.usersearch.UserSearchActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class GroupDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupDetailBinding

    private val groupId: Long
        get() = intent.getLongExtra(KEY_GROUP_ID, Long.MIN_VALUE)

    private val groupDetailViewModel: GroupDetailsViewModel by viewModels {
        GroupDetailsViewModelFactory(groupId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.group_details_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_detail)

        binding.lifecycleOwner = this

        binding.viewModel = groupDetailViewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        groupDetailViewModel.groupName.observe(this){
            this.title = it
        }

        groupDetailViewModel.isDeleted.observe(this) {
            if (it == true){
                finish()
            }
        }

        binding.groupDetailActivityActiveEventRecyclerView.adapter = ActiveEventsRecyclerViewAdapter()

        binding.groupDetailActivityMemberRecyclerView.adapter = MemberListRecyclerViewAdapter()

        binding.groupDetailActivityLogRecyclerView.adapter = LogListRecyclerViewAdapter()

        binding.groupDetailActivityCreateEventButton.setOnClickListener {
            CreateEventActivity.newInstance(this, groupId)
        }

        binding.groupDetailActivityAddRemoveButton.setOnClickListener {
            UserSearchActivity.newInstance(this, groupId)
        }

        groupDetailViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.groupDetailsActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.setTextMaxLines(10)
                    it.show()
                }
            }
        }

        groupDetailViewModel.success.observe(this) {
            if (it == true){
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.group_menu_item_update_group -> {
                if (groupDetailViewModel.isGroupOwner.value == true) {
                    UpdateGroupActivity.newInstance(this, groupId)
                    false
                } else {
                    false
                }
            }
            R.id.group_menu_item_exit_from_group -> {
                MaterialAlertDialogBuilder(this)
                    .setMessage(resources.getString(R.string.sure_exit_from_group))
                    .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                        groupDetailViewModel.exitFromGroup()
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        if (groupId == Long.MIN_VALUE){
            finish()
        }
        groupDetailViewModel.getGroupDetails()
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
