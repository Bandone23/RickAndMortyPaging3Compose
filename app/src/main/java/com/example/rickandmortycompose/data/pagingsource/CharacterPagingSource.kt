package com.example.rickandmortycompose.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycompose.data.remote.ApiService
import com.example.rickandmortycompose.presentation.model.CharacterModel
import java.io.IOException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(private val api: ApiService):
    PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val page: Int = params.key ?: 1
            val response = api.getCharacters(page)
            val characters = response.results

            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (response.information.next != null) page + 1 else null

            LoadResult.Page(
                data = characters.map { it.toPresentationModel() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

}