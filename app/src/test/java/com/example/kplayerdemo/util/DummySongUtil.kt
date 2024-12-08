package com.example.kplayerdemo.util

import com.example.kplayerdemo.domain.model.Song

object DummySongUtil {
    fun prepareDummyData(): List<Song> {
        val songs = mutableListOf<Song>()
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
        return songs
    }
}