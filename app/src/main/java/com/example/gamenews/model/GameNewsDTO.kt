package com.example.gamenews.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameNewsDTO(
    @SerializedName("title") val title: String = "",
    @SerializedName("date") val date: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("image") val image: String = "",
    @SerializedName("link") val link: String = "",
) : Parcelable
