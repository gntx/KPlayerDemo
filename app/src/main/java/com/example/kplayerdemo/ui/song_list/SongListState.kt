package com.example.kplayerdemo.ui.song_list

import com.example.kplayerdemo.domain.model.Song

data class SongListState(
    val isLoading: Boolean = false,
    val songs: List<Song> = emptyList(),
    val error: String = ""
)
