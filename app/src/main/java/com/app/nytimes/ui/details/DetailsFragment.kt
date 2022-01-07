package com.app.nytimes.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.app.nytimes.data.Feed
import com.app.nytimes.databinding.FragmentDetailsBinding
import com.app.nytimes.ui.MainVM
import com.app.nytimes.ui.common.BaseFragment

class DetailsFragment : BaseFragment() {

    private val viewModel: MainVM by activityViewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mFeed: Feed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get arguments to pass data from other fragments.
        val args: DetailsFragmentArgs by navArgs()
        // Keep showing post.
        mFeed = args.feed
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.vm = viewModel

        setupUI()

        return binding.root

    }

    private fun setupUI() {
        binding.feed = mFeed
        binding.webView.webViewClient = WebViewClient()

        binding.webView.loadUrl(mFeed.url)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}