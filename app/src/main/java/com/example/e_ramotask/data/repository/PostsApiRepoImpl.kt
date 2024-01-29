package com.example.e_ramotask.data.repository

import com.example.e_ramotask.data.model.response.GetPostsResponse
import com.example.e_ramotask.data.remote.ApiInterface
import com.example.e_ramotask.domain.repository.PostsApIRepo
import javax.inject.Inject

class PostsApiRepoImpl @Inject constructor(private val api: ApiInterface) : PostsApIRepo {
    override suspend fun getPosts(): MutableList<GetPostsResponse> {
        return api.getPostsApi()
    }
}