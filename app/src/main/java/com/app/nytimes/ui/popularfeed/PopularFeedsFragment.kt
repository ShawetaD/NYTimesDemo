package com.app.nytimes.ui.popularfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.nytimes.data.Feed
import com.app.nytimes.data.Resource
import com.app.nytimes.databinding.FragmentHomeBinding
import com.app.nytimes.ui.MainVM
import com.app.nytimes.ui.adapter.FeedsAdapter
import com.app.nytimes.ui.common.BaseFragment
import com.app.nytimes.ui.common.navigateTo


class PopularFeedsFragment : BaseFragment() {
    private val viewModel: MainVM by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupObservers()
        viewModel.callToGetPopularFeeds(7)
        return binding.root
    }

    private fun setupObservers() {
        var mAdapter: FeedsAdapter

        binding.componentList.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = FeedsAdapter(onItemClick = { feed ->
                navigateTo(PopularFeedsFragmentDirections.actionFeedFragmentToDetailsFragment(feed))
            })
            adapter = mAdapter
        }

        viewModel.feedList.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        mAdapter.submitList(it.data?.toMutableList())
                        callLoadingProgressBar(false)
                    }
                    Resource.Status.ERROR -> {
                        callLoadingProgressBar(false)
                    }
                    else -> {
                        //do nothing
                    }
                }
            }
        })
    }

    // Manage progress bar.
    private fun callLoadingProgressBar(isLoading: Boolean) {
        viewModel.isLoading.postValue(isLoading)
        if (isLoading) binding.progressBar1.visibility = VISIBLE else binding.progressBar1.visibility = GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}