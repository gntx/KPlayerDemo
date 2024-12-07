package com.example.kplayerdemo.data.repository

import com.example.kplayerdemo.domain.model.Song
import com.example.kplayerdemo.domain.repository.SongRepository

class FakeSongRepository : SongRepository {

    private val songs = mutableListOf<Song>()

    init {
        ('a'..'z').forEachIndexed { index, c ->
            val song = Song(
                trackId = index,
                trackName = c.toString(),
                collectionId = index,
                collectionName = c.toString(),
                artworkUrl30 = c.toString(),
                artworkUrl60 = c.toString(),
                artworkUrl100 = c.toString()
                )
            songs.add(song)
        }
    }

    override suspend fun searchSongsByArtistName(artistName: String): List<Song> {
        return songs
    }

    fun getFakeSongs(): List<Song> {
        return songs
    }
}