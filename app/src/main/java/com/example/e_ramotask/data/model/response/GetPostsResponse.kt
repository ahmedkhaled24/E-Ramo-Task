package com.example.e_ramotask.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

typealias Posts = MutableList<GetPostsResponse>
data class GetPostsResponse(
    @Expose
    @SerializedName("userId")
    var userId: Int,
    @Expose
    @SerializedName("id")
    var id: Int,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("body")
    val body: String
)
