package com.example.kplayerdemo.domain.repository

import com.example.kplayerdemo.domain.model.Song

interface SongRepository {
    suspend fun searchSongsByArtistName(artistName: String): List<Song>
}