package com.example.kplayerdemo.domain.repository

import com.example.kplayerdemo.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    suspend fun searchSongsByArtistName(artistName: String): List<Song>
    suspend fun upsertSongs(songs: List<Song>)
    suspend fun getAllSongs(): List<Song>
    suspend fun deleteAllSongs()
}