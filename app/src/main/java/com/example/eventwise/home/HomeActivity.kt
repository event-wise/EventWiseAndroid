package com.example.eventwise.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.eventwise.R
import com.example.eventwise.databinding.ActivityHomeBinding
import com.example.eventwise.home.events.HomeEventsFragment
import com.example.eventwise.home.groups.HomeGroupsFragment
import com.example.eventwise.home.user.HomeUserFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setupViewPager()
    }

    private fun setupViewPager() {
        binding.activityHomeViewPager.adapter = HomeViewPagerAdapter(this)

        TabLayoutMediator(
            binding.activityHomeTabLayout,
            binding.activityHomeViewPager
        ) { tab, position ->
            getTabText(position)?.let { tab.setText(it) }
        }.attach()
    }

    private fun getTabText(position: Int): Int? {
        return when (position) {
            EVENTS_PAGE_INDEX -> R.string.events
            GROUPS_PAGE_INDEX -> R.string.groups
            USER_PAGE_INDEX -> R.string.user
            else -> null
        }
    }

    companion object {
        private const val EVENTS_PAGE_INDEX = 0
        private const val GROUPS_PAGE_INDEX = 1
        private const val USER_PAGE_INDEX = 2
    }
}

private class HomeViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = TAB_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeEventsFragment.newInstance()
            1 -> HomeGroupsFragment.newInstance()
            else -> HomeUserFragment.newInstance()
        }
    }

    companion object {
        const val TAB_COUNT = 3
    }
}
