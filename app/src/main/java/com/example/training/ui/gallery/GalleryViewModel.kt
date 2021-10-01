package com.example.training.ui.gallery


import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.training.data.picturesApiRepository
import com.example.training.models.picture

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val respository: picturesApiRepository,state: SavedStateHandle) :
    ViewModel() {

    private var currentResult = state.getLiveData<PagingData<picture>>(save_QUERY,null).asFlow()

    fun searchPictures(): Flow<PagingData<picture>> {
        val newResult: Flow<PagingData<picture>> =
            respository.getSearchResults().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
companion object{

    private const val save_QUERY = "default_query"
    private const val defaul_QUERY = "cats"
}

}