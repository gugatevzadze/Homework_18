package com.example.homework_18.user

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework_18.service.ApiService
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        try {
            //extracting page number from the LoadParams or default to page 1
            val page = params.key ?: 1
            //making an API request to fetch user data for the specified page
            val response = apiService.getUsers(page)

            //returning a LoadResult containing the loaded data, previous key, and next key
            return LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < response.total_pages) page + 1 else null
            )
        } catch (e: IOException) {
            //handling IOException for network failures
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            //handling HttpException
            return LoadResult.Error(e)
        } catch (e: Exception) {
            //handling other exceptions by returning an error result with a throwable
            return LoadResult.Error(Throwable("Error loading page", e))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

}
