package com.example.eventwise.screens.home.groups

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.eventwise.R
import com.example.eventwise.databinding.FragmentHomeGroupsBinding
import com.example.eventwise.screens.creategroup.CreateGroupActivity


class HomeGroupsFragment : Fragment() {

    private lateinit var binding: FragmentHomeGroupsBinding

    private val viewModel by viewModels<HomeGroupsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_groups, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.fragmentHomeGroupsGroupRecyclerView.adapter = GroupsRecyclerViewAdapter()

        binding.fragmentHomeGroupsCreateGroupButton.setOnClickListener {
            startActivity(Intent(requireActivity(), CreateGroupActivity::class.java))
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshGroupList()
    }

    companion object {

        fun newInstance(): HomeGroupsFragment {
            return HomeGroupsFragment().apply {
                // TODO
            }
        }
    }
}
