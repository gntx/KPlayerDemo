package com.example.kplayerdemo.data.repository

import com.example.kplayerdemo.data.remote.SearchAPI
import com.example.kplayerdemo.data.remote.dto.SongListResponse
import com.example.kplayerdemo.domain.repository.SongRepository
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val api: SearchAPI
) : SongRepository {
    override suspend fun searchSongsByArtistName(artistName: String): SongListResponse {
        return api.search(artistName)
    }
}