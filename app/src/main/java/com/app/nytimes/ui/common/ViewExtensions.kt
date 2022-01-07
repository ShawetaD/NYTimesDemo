package com.app.nytimes.ui.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.app.nytimes.R

val View.screenLocation
    get(): IntArray {
        val point = IntArray(2)
        getLocationOnScreen(point)
        return point
    }

//Simple Extension for showing horizontal line dividers
fun RecyclerView.addDividers(orientation: Int = DividerItemDecoration.VERTICAL) {
    addItemDecoration(
        DividerItemDecoration(
            context,
            orientation
        )
    )
}

fun RecyclerView.onBottomReached(callback: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1)) {
                callback()
            }
        }
    })
}

/**
 * Infinite scroll listener.
 * @scrolling use to fetch data and understand how many item you need to fetch.
 * @comeEnd use to show message and get message resource id.
 */
fun RecyclerView.addInfiniteScrollListener(scrolling: (Int) -> Unit, comeEnd: (Int) -> Unit) {
    var currentListSize = 0

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            // Scroll down is +1, up is -1.
            if (!recyclerView.canScrollVertically(1)
                && newState == RecyclerView.SCROLL_STATE_IDLE
            ) {
                val listSizeAfterLoading = recyclerView.layoutManager!!.itemCount

                // List has more item.
                if (currentListSize != listSizeAfterLoading) {
                    currentListSize = listSizeAfterLoading

                    // Get more items as count of listSizeAfterLoading.
                    scrolling(listSizeAfterLoading)
                } else comeEnd(R.string.infinite_scroll_message_finish) // List comes limit.
            }
        }
    })
}