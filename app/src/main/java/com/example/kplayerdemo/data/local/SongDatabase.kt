package com.example.kplayerdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kplayerdemo.data.local.dao.SongDao
import com.example.kplayerdemo.domain.model.Song

@Database(
    entities = [Song::class],
    version = 1
)
abstract class SongDatabase : RoomDatabase() {

    abstract val songDao: SongDao

    companion object {
        const val DATABASE_NAME = "songs_db"
    }
}