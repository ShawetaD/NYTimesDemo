package com.app.nytimes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.nytimes.data.Feed
import com.app.nytimes.data.Media
import com.app.nytimes.databinding.ItemPostBinding


class FeedsAdapter(
    private val onItemClick: (Feed) -> Unit
) :
    ListAdapter<Feed, FeedsAdapter.ResultsViewHolder>(
        object : DiffUtil.ItemCallback<Feed>() {
            override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    class ResultsViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            feed: Feed,
            index: Int,
            onItemClick: (Feed) -> Unit
        ) {
            binding.apply {
                item = feed
                if (feed.media.isNotEmpty()) {
                    try {
                        val media: Media = feed.media[0]
                        val mediaIndex: Int = media.mediaMetadats?.size!! - 1
                        imageUrl = if (mediaIndex > 0) {
                            media.mediaMetadats[mediaIndex].url
                        } else media.mediaMetadats[0].url
                    } catch (ex: Exception) {

                    }
                }

                root.setOnClickListener { onItemClick(feed) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflator, parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(getItem(position), position, onItemClick)
    }
}