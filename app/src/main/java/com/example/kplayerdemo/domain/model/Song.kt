package com.example.kplayerdemo.domain.model

data class Song(
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val collectionId: Int,
    val collectionName: String,
    val trackId: Int,
    val trackName: String
)
