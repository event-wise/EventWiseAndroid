package com.example.eventwise.screens.home.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.eventwise.R
import com.example.eventwise.databinding.FragmentHomeUserBinding
import com.example.eventwise.screens.changepassword.ChangePasswordActivity
import com.example.eventwise.screens.login.LoginActivity
import com.example.eventwise.services.Constants
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class HomeUserFragment : Fragment() {

    private lateinit var binding : FragmentHomeUserBinding

    private val viewModel by viewModels<HomeUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_user, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.homeUserFragmentChangePasswordButton.setOnClickListener {
            startActivity(Intent(requireActivity(), ChangePasswordActivity::class.java))
        }

        binding.homeUserFragmentLogOutButton.setOnClickListener {
            requireActivity().finish()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }

        binding.homeUserFragmentSaveButton.setOnClickListener {
            viewModel.updateProfileInformation()
            binding.homeUserFragmentEditTextDisplayedUsername.clearFocus()
        }

        binding.homeUserFragmentLogOutButton.setOnClickListener {
            viewModel.logOut()
            Constants.BEARER_TOKEN = "Bearer token"
            Constants.GLOBAL_USER_ID = -1
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }

        binding.homeUserFragmentDeleteAccountButton.setOnClickListener {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setMessage(resources.getString(R.string.sure_delete_account))
                    .setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(resources.getString(R.string.yes)) { dialog, which ->
                        viewModel.deleteAccount()
                        requireActivity().finish()
                        startActivity(Intent(requireActivity(), LoginActivity::class.java))
                    }
                    .show()
            }
        }

        viewModel.errorMessage.observe(requireActivity()) { error ->
            if (error != null) {
                Snackbar.make(binding.homeUserFragmentLayout, "", Snackbar.LENGTH_SHORT).also {
                    it.setText(error)
                    it.setTextMaxLines(10)
                    it.show()
                }
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.retrieveUserInformation()
    }

    companion object {

        fun newInstance(): HomeUserFragment {
            return HomeUserFragment().apply {
                // TODO
            }
        }
    }
}
