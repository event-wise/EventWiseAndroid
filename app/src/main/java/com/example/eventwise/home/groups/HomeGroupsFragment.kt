package com.example.eventwise.home.groups

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.eventwise.R
import com.example.eventwise.databinding.FragmentHomeEventsBinding
import com.example.eventwise.databinding.FragmentHomeGroupsBinding


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

        return binding.root
    }

    companion object {

        fun newInstance(): HomeGroupsFragment {
            return HomeGroupsFragment().apply {
                // TODO
            }
        }
    }
}
