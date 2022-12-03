package com.example.eventwise.screens.home.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.eventwise.R
import com.example.eventwise.databinding.FragmentHomeEventsBinding


class HomeEventsFragment : Fragment() {

    private val viewModel by viewModels<HomeEventsViewModel>()

    private lateinit var binding: FragmentHomeEventsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_events, container, false)

        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.fragmentHomeEventsEventRecyclerView.adapter = EventsRecyclerViewAdapter()

        return binding.root
    }

    companion object {

        fun newInstance(): HomeEventsFragment {
            return HomeEventsFragment().apply {
                // TODO
            }
        }
    }
}
