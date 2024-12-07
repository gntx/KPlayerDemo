package com.example.kplayerdemo.ui.song_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kplayerdemo.domain.use_case.SearchSongsUseCase
import com.example.kplayerdemo.util.Constants
import com.example.kplayerdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val searchSongsUseCase: SearchSongsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SongListState())
    val state = _state.asStateFlow()

    var queryString by mutableStateOf("")
        private set

    fun updateQueryString(input: String) {
        queryString = input
        _state.update {
            it.copy(queryString = input)
        }
    }

    init {
        getSongs()
    }

    private fun getSongs() {
        viewModelScope.launch {
            searchSongsUseCase(artistName = Constants.DEFAULT_ARTIST_NAME).collect { result ->
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

            }
        }
    }
}