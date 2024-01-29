package com.example.e_ramotask.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_ramotask.data.model.response.GetPostsResponse
import com.example.e_ramotask.domain.usecases.PostsUseCase
import com.example.e_ramotask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor(private val postsUseCase: PostsUseCase) : ViewModel() {

    private val _postsState: MutableLiveData<Resource<MutableList<GetPostsResponse>>> = MutableLiveData()
    val postsData: LiveData<Resource<MutableList<GetPostsResponse>>> = _postsState

    fun postsData() {
        viewModelScope.launch {
            postsUseCase().collect {
                _postsState.postValue(it)
            }
        }
    }

}