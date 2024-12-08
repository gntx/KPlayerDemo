package com.example.kplayerdemo.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.kplayerdemo.domain.model.Song

@Dao
interface SongDao {

    @Upsert
    suspend fun upsertSongs(songs: List<Song>)

    @Query("SELECT * FROM songs")
    suspend fun getAllSongs(): List<Song>

    @Query("DELETE FROM songs")
    suspend fun deleteAllSongs()

}