package com.oddlycoder.newshq.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class News(
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("urls")
    val urls: Urls,
    @JsonProperty("articles")
    val articles: List<Article>
)

data class Urls(
    @JsonProperty("self")
    val self: String,
    @JsonProperty("next")
    val next: String,
    @JsonProperty("prev")
    val prev: String
)

data class Article (
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("url")
    val url: String?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("text")
    val text: String?,
    @JsonProperty("publisher")
    val publisher: String?,
    @JsonProperty("author")
    val author: String?,
    @JsonProperty("image")
    val image: String?,
    @JsonProperty("date")
    val date: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeString(text)
        parcel.writeString(publisher)
        parcel.writeString(author)
        parcel.writeString(image)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }

}