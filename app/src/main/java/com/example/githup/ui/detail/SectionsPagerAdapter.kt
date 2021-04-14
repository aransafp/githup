package com.example.githup.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity, private val username: String?) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }

        fragment?.arguments = Bundle().apply {
            if (fragment is FollowerFragment) {
                putString(FollowerFragment.ARGS_USERNAME, username)
            } else if (fragment is FollowingFragment) {
                putString(FollowingFragment.ARGS_USERNAME, username)
            }
        }

        return fragment as Fragment
    }

}