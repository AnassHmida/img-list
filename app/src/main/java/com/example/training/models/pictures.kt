package com.example.training.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class pictures(
    val results: ArrayList<picture> = ArrayList<picture>()
)

@Parcelize
data class picture(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
) : Parcelable