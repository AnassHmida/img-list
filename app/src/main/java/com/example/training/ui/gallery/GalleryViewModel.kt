package com.example.training.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.training.api.picturesAPI
import com.example.training.data.picturesApiRepository
import com.example.training.models.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch



class GalleryViewModel @ViewModelInject constructor(private val respository:  picturesApiRepository): ViewModel(){

    private val submitEvent = MutableSharedFlow<Unit>()
    //val photos = respository.getSearchResults()

    private val resource = submitEvent
        .flatMapLatest { respository.getSearchResults() }
        .stateIn(viewModelScope, SharingStarted.Eagerly,Resource.Loading )

    fun submit() {
        viewModelScope.launch {
            submitEvent.emit(Unit)
        }
    }
}