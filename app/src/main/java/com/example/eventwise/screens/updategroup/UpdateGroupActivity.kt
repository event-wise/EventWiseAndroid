package com.example.eventwise.screens.updategroup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityUpdateGroupBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class UpdateGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateGroupBinding

    private val groupId: Long
        get() = intent.getLongExtra(KEY_GROUP_ID, Long.MIN_VALUE)

    private val updateGroupViewModel: UpdateGroupViewModel by viewModels{
        UpdateGroupViewModelFactory(groupId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_group)

        binding.lifecycleOwner = this

        binding.viewModel = updateGroupViewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        updateGroupViewModel.errorMessage.observe(this) { error ->
            if (error != null) {
                Snackbar.make(binding.updateGroupActivityLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.show()
                }
            }
        }

        updateGroupViewModel.success.observe(this) {
            if (it == true){
                finish()
            }
        }

        binding.updateGroupActivityCancelButton.setOnClickListener {
            finish()
        }

        binding.updateGroupActivitySaveButton.setOnClickListener {
            updateGroupViewModel.updateGroup()
        }

        binding.updateGroupActivityDeleteGroupButton.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setMessage(resources.getString(R.string.sure_delete_event))
                .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
                    dialog.dismiss()
                }
                .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                    updateGroupViewModel.deleteGroup()
                }
                .show()
        }

        updateGroupViewModel.groupOwner.observe(this) {
            if (it == false){
                updateGroupViewModel.errorMessage.value = "You can not edit this group!"
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
        private const val KEY_GROUP_ID = "group_id_update"

        val newInstance = { context: Context, groupId: Long ->
            val intent = Intent(context, UpdateGroupActivity::class.java)
            intent.putExtra(KEY_GROUP_ID, groupId)
            context.startActivity(intent)
        }
    }
}
