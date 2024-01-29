package com.example.e_ramotask.data.repository

import com.example.e_ramotask.data.model.response.GetPostsResponse
import com.example.e_ramotask.data.remote.ApiInterface
import com.example.e_ramotask.domain.repository.PostDetailsApIRepo
import javax.inject.Inject

class PostDetailsApiRepoImpl @Inject constructor(private val api: ApiInterface) : PostDetailsApIRepo {
    override suspend fun getPostDetails(id: Int): GetPostsResponse {
        return api.getPostDetailsApi(id)
    }
}