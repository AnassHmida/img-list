package com.example.training.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.training.api.picturesAPI
import com.example.training.data.Paging.picturesApiPagingSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class picturesApiRepository @Inject constructor(private val pictureapi: picturesAPI) {


fun getSearchResults() = Pager(
    config= PagingConfig(
        pageSize = 20,
        maxSize = 100,
        enablePlaceholders = false
    )
,

    pagingSourceFactory = {picturesApiPagingSource(pictureapi) }

).flow


}