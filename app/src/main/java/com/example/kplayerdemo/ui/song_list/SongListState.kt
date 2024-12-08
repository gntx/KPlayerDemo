package com.example.kplayerdemo.ui.song_list

import com.example.kplayerdemo.domain.model.Song
import com.example.kplayerdemo.util.SortingType

data class SongListState(
    val isLoading: Boolean = false,
    val songs: List<Song> = emptyList(),
    val error: String = "",
    var queryString: String = "",
    var selectedSortingType: SortingType = SortingType.BY_SONG
) {
    val displaySongs: List<Song>
        get() {
            val sortedSongs = songs.sortedBy {
                when (selectedSortingType) {
                    SortingType.BY_SONG -> it.trackName
                    SortingType.BY_ALBUM -> it.collectionName
                }
            }
            return if (queryString != "") {
                sortedSongs.filter {
                    it.trackName.lowercase().contains(queryString.lowercase())
                            || it.collectionName.lowercase().contains(queryString.lowercase())
                }
            } else {
                sortedSongs
            }
        }
}
