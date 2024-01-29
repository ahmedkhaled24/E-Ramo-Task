package com.example.e_ramotask.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_ramotask.data.model.response.GetPostsResponse
import com.example.e_ramotask.domain.usecases.PostDetailsUseCase
import com.example.e_ramotask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostDetailsViewModel @Inject constructor(private val postDetailsUseCase: PostDetailsUseCase) : ViewModel() {

    private val _postDetailsUseCaseState: MutableLiveData<Resource<GetPostsResponse>> = MutableLiveData()
    val postDetailsUseCaseData: LiveData<Resource<GetPostsResponse>> = _postDetailsUseCaseState

    fun postDetailsData(id: Int) {
        viewModelScope.launch {
            postDetailsUseCase(id).collect {
                _postDetailsUseCaseState.postValue(it)
            }
        }
    }

}
