package com.app.nytimes.ui.common

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout

fun createTabSelectedListener(tabSelectedBlock: (position: Int?) -> Unit): TabLayout.OnTabSelectedListener {
    return object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            tabSelectedBlock(tab?.position)
        }

    }
}

fun Fragment.navigateTo(directions: NavDirections) {
    try {
        findNavController().navigate(directions)
    }
    catch (e: IllegalArgumentException) {
        Log.e("Fragment","Catching potential duplicate navigation event")
    }
}