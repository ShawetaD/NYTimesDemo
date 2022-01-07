package com.app.nytimes.data

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedResponse(
    @field:SerializedName("status")
    var status: String,
    @field:SerializedName("num_results")
    var num_results: Int,
    @field:SerializedName("results")
    val results: List<Feed>
) : Parcelable {
    // Upload image (First look cache, then send request).

}

@Parcelize
data class Feed(
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("abstract")
    val abstract: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("byline")
    val byline: String,
    @field:SerializedName("section")
    val section: String,
    @field:SerializedName("adx_keywords")
    val adx_keywords: String,
    @field:SerializedName("published_date")
    val publishedDate: String,
    @field:SerializedName("source")
    val source: String,
    @field:SerializedName("media")
    val media: List<Media>
) : Parcelable {

}

@Parcelize
data class Media(
    @field:SerializedName("caption")
    val caption: String,
    @field:SerializedName("copyright")
    val copyright: String,
    @field:SerializedName("media-metadata")
    val mediaMetadats: List<MediaData>?
) : Parcelable {

}

@Parcelize
data class MediaData(
    @field:SerializedName("url")
    val url: String
) : Parcelable {


}