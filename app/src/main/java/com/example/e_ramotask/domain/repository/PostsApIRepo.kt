package com.example.e_ramotask.domain.repository

import com.example.e_ramotask.data.model.response.GetPostsResponse


interface PostsApIRepo {
    suspend fun getPosts(): MutableList<GetPostsResponse>
}