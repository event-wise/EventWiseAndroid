package com.example.eventwise.home.groups

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.eventwise.R


class HomeGroupsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_groups, container, false)
    }

    companion object {

        fun newInstance(): HomeGroupsFragment {
            return HomeGroupsFragment().apply {
                // TODO
            }
        }
    }
}
