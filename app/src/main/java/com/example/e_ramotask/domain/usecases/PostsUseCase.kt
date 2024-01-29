package com.example.e_ramotask.domain.usecases

import android.util.Log
import com.example.e_ramotask.domain.repository.PostsApIRepo
import com.example.e_ramotask.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


private const val TAG = "TagPostsUseCase"
class PostsUseCase @Inject constructor(private val repository: PostsApIRepo) {

    operator fun invoke() = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getPosts()
            emit(Resource.Success(data = response))

        } catch (e: HttpException) {
            Log.d(TAG, "error HttpException: ${e.message}")
            Log.d(TAG, "error HttpException code: ${e.code()}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {
            Log.d(TAG, "error IOException: ${e.message}")
            emit(Resource.Error("Couldn't reach server. Please check your internet connection"))
        }
    }
}