package com.example.eventwise.screens.usersearch

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityUserSearchBinding
import com.example.eventwise.screens.createevent.CreateEventActivity
import com.example.eventwise.screens.groupdetails.GroupDetailActivity
import com.google.android.material.snackbar.Snackbar

class UserSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserSearchBinding

    private val groupId: Long
        get() = intent.getLongExtra(KEY_GROUP_ID_SEARCH, Long.MIN_VALUE)

    private val userSearchViewModel: UserSearchViewModel by viewModels {
        UserSearchViewModelFactory(groupId = groupId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_search)

        binding.lifecycleOwner = this

        binding.viewModel = userSearchViewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        userSearchViewModel.userFound.observe(this){
            binding.userSearchActivityFoundedPeopleLayout.visibility = if (it == true){
                 View.VISIBLE
            } else {
                View.GONE
            }
        }

        userSearchViewModel.isMember.observe(this){
            binding.activityUserSearchAddRemoveButton.text = if (it == true){
                getString(R.string.remove_from_group)
            } else {
                getString(R.string.add_to_group)
            }
        }

        binding.userSearchActivitySearchPeopleButton.setOnClickListener {
            userSearchViewModel.searchMember()
        }

        binding.activityUserSearchAddRemoveButton.setOnClickListener {
            userSearchViewModel.addRemoveMember()
        }

        userSearchViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.userSearchActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.show()
                }
            }
        }

        userSearchViewModel.success.observe(this) {
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
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val KEY_GROUP_ID_SEARCH = "KEY_GROUP_ID_SEARCH"

        val newInstance = { context: Context, groupId: Long ->
            val intent = Intent(context, UserSearchActivity::class.java)
            intent.putExtra(KEY_GROUP_ID_SEARCH, groupId)
            context.startActivity(intent)
        }
    }
}
