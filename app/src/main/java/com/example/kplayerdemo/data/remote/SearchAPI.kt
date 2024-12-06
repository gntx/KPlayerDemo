package com.example.kplayerdemo.data.remote

import com.example.kplayerdemo.data.remote.dto.SongListResponse
import com.example.kplayerdemo.util.Constants.Companion.DefaultLimitCount
import com.example.kplayerdemo.util.Constants.Companion.DefaultMediaType
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {
    @GET("search")
    suspend fun search(
        @Query("term")
        term: String,
        @Query("limit")
        limitCount: Int = DefaultLimitCount,
        @Query("media")
        media: String = DefaultMediaType
    ): SongListResponse
}