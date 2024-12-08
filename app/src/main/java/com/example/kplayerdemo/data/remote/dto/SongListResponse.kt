package com.example.kplayerdemo.data.remote.dto

data class SongListResponse(
    val resultCount: Int,
    val results: List<Result>
)