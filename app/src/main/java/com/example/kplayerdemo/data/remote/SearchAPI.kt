package com.example.kplayerdemo.data.remote

import com.example.kplayerdemo.data.remote.dto.SongListResponse
import com.example.kplayerdemo.util.Constants.Companion.DEFAULT_LIMIT_COUNT
import com.example.kplayerdemo.util.Constants.Companion.DEFAULT_MEDIA_TYPE
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("search")
    suspend fun search(
        @Query("term")
        term: String,
        @Query("limit")
        limitCount: Int = DEFAULT_LIMIT_COUNT,
        @Query("media")
        media: String = DEFAULT_MEDIA_TYPE
    ): SongListResponse
}