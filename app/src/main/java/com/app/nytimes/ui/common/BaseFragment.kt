package com.app.nytimes.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

abstract class BaseFragment : Fragment() {

    fun getThisActivity(): BaseActivity = requireActivity() as BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val baseActivity: BaseActivity
        get() {
            val activity: FragmentActivity? = activity
            if (activity is BaseActivity) {
                return activity
            }
            throw RuntimeException("BaseActivity is null")
        }

}