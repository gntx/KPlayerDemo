package com.example.kplayerdemo.data.repository

import com.example.kplayerdemo.data.local.SongDatabase
import com.example.kplayerdemo.data.remote.SearchAPI
import com.example.kplayerdemo.data.remote.dto.toSong
import com.example.kplayerdemo.domain.model.Song
import com.example.kplayerdemo.domain.repository.SongRepository
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val api: SearchAPI,
    private val db: SongDatabase
) : SongRepository {
    override suspend fun searchSongsByArtistName(artistName: String): List<Song> {
        return api.search(artistName).results.map { it.toSong()}
    }

    override suspend fun upsertSongs(songs: List<Song>) = db.songDao.upsertSongs(songs)

    override suspend fun getAllSongs() = db.songDao.getAllSongs()

    override suspend fun deleteAllSongs() = db.songDao.deleteAllSongs()
}