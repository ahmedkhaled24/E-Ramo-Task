package com.example.e_ramotask.data.remote

import com.example.e_ramotask.data.model.response.GetPostsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("posts")
    suspend fun getPostsApi()
    : MutableList<GetPostsResponse>

    @GET("posts/{id}")
    suspend fun getPostDetailsApi(
        @Path("id") id: Int
    ): GetPostsResponse


}