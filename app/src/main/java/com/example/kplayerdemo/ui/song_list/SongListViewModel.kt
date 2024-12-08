package com.example.kplayerdemo.ui.song_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kplayerdemo.domain.use_case.GetAllSongsUseCase
import com.example.kplayerdemo.domain.use_case.SearchSongsUseCase
import com.example.kplayerdemo.domain.use_case.UpdateSongsUseCase
import com.example.kplayerdemo.util.Constants
import com.example.kplayerdemo.util.Resource
import com.example.kplayerdemo.util.SortingType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val searchSongsUseCase: SearchSongsUseCase,
    private val updateSongsUseCase: UpdateSongsUseCase,
    private val getAllSongsUseCase: GetAllSongsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SongListState())
    val state = _state.asStateFlow()

    init {
        getSongs()
    }

    private fun getSongs() {
        viewModelScope.launch {
            searchSongsUseCase(artistName = Constants.DEFAULT_ARTIST_NAME).collect { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = SongListState(songs = result.data ?: emptyList())

                        // Update db cached data
                        result.data?.let { songs ->
                            viewModelScope.launch {
                                updateSongsUseCase(songs)
                            }
                        }
                    }
                    is Resource.Error -> {
                        // Read cache from db
                        viewModelScope.launch {
                            val cachedSongs = getAllSongsUseCase()
                            _state.value = SongListState(
                                songs = cachedSongs ?: emptyList(),
                                error = result.message ?: "An unexpected error occurred."
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _state.value = SongListState(isLoading = true)
                    }
                }
            }
        }
    }

    fun updateQueryString(input: String) {
        _state.update {
            it.copy(queryString = input)
        }
    }

    fun updateSortingType(option: SortingType) {
        _state.update {
            it.copy(selectedSortingType = option)
        }
    }

    fun refreshSongList() {
        getSongs()
    }
}