package com.example.eventwise.home.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.eventwise.R


class HomeEventsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_events, container, false)
    }

    companion object {

        fun newInstance(): HomeEventsFragment {
            return HomeEventsFragment().apply {
                // TODO
            }
        }
    }
}
