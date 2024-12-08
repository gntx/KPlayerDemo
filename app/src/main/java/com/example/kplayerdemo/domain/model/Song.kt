package com.example.kplayerdemo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "songs"
)
data class Song(
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val collectionId: Int,
    val collectionName: String,
    @PrimaryKey
    val trackId: Int,
    val trackName: String
)
