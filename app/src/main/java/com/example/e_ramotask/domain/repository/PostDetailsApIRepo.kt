package com.example.e_ramotask.domain.repository

import com.example.e_ramotask.data.model.response.GetPostsResponse

interface PostDetailsApIRepo {
    suspend fun getPostDetails(id: Int): GetPostsResponse
}