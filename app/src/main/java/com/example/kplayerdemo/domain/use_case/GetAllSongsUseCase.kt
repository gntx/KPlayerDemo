package com.example.kplayerdemo.domain.use_case

import com.example.kplayerdemo.domain.model.Song
import com.example.kplayerdemo.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSongsUseCase @Inject constructor(
    private val repository: SongRepository
) {
    suspend operator fun invoke(): List<Song> {
        return repository.getAllSongs()
    }
}