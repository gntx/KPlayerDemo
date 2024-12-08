package com.example.kplayerdemo.data.repository

import com.example.kplayerdemo.domain.model.Song
import com.example.kplayerdemo.domain.repository.SongRepository
import com.example.kplayerdemo.util.DummySongUtil

class FakeSongRepository : SongRepository {

    private val songs = mutableListOf<Song>()

    init {
        val initSongs = DummySongUtil.prepareDummyData()
        initSongs.forEach { song ->
            songs.add(song)
        }
    }

    override suspend fun searchSongsByArtistName(artistName: String): List<Song> {
        return songs
    }

    override suspend fun upsertSongs(songs: List<Song>) {
        songs.forEach { song ->
            this.songs.add(song)
        }
    }

    override suspend fun getAllSongs(): List<Song> {
        return songs
    }

    override suspend fun deleteAllSongs() {
        songs.clear()
    }
}