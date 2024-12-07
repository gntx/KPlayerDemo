package com.example.kplayerdemo.ui.song_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kplayerdemo.domain.use_case.SearchSongsUseCase
import com.example.kplayerdemo.util.Constants
import com.example.kplayerdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val searchSongsUseCase: SearchSongsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SongListState())
    val state: State<SongListState> = _state

    init {
        getSongs()
    }

    private fun getSongs() {
        searchSongsUseCase(artistName = Constants.DEFAULT_ARTIST_NAME).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = SongListState(songs = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = SongListState(
                        error = result.message ?: "An unexpected error occurred."
                    )
                }
                is Resource.Loading -> {
                    _state.value = SongListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}