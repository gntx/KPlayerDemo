package com.example.kplayerdemo.domain.repository

import com.example.kplayerdemo.data.remote.dto.SongListResponse

interface SongRepository {
    suspend fun searchSongsByArtistName(artistName: String): SongListResponse
}