package com.example.eventwise.home.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.eventwise.R
import com.example.eventwise.databinding.FragmentHomeUserBinding
import com.example.eventwise.home.events.HomeEventsViewModel


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

        return binding.root
    }

    companion object {

        fun newInstance(): HomeUserFragment {
            return HomeUserFragment().apply {
                // TODO
            }
        }
    }
}
