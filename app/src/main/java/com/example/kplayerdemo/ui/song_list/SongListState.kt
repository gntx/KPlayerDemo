package com.example.kplayerdemo.ui.song_list

import com.example.kplayerdemo.domain.model.Song

data class SongListState(
    val isLoading: Boolean = false,
    val songs: List<Song> = emptyList(),
    val error: String = "",
    var queryString: String = ""
) {
    val displaySongs: List<Song>
        get() {
            return if (queryString != "") {
                songs.filter {
                    it.trackName.lowercase().contains(queryString.lowercase())
                            || it.collectionName.lowercase().contains(queryString.lowercase())
                }
            } else {
                songs
            }
        }
}
