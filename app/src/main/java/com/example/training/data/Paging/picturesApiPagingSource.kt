package com.example.training.data.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.training.api.picturesAPI
import com.example.training.models.picture
import com.example.training.models.pictures
import retrofit2.HttpException
import java.io.IOException

private const val PICTURE_STARTING_PAGE_INDEX = 1
class picturesApiPagingSource(private val picturesAPI: picturesAPI):PagingSource<Int,picture>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, picture> {
        val position  = params.key?: PICTURE_STARTING_PAGE_INDEX

        return try {
            val response = picturesAPI.searchPhotos()
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == PICTURE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1

            )
        } catch (exception : IOException){
LoadResult.Error(exception)
        } catch (exception : HttpException){
            LoadResult.Error(exception)


    }



    }

    override fun getRefreshKey(state: PagingState<Int, picture>): Int? {
        TODO("Not yet implemented")
    }


}