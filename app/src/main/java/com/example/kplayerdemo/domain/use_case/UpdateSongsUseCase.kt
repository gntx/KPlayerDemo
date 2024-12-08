package com.example.kplayerdemo.domain.use_case

import com.example.kplayerdemo.domain.model.Song
import com.example.kplayerdemo.domain.repository.SongRepository
import javax.inject.Inject

class UpdateSongsUseCase @Inject constructor(
    private val repository: SongRepository
) {
    suspend operator fun invoke(songs: List<Song>) {
        repository.deleteAllSongs()
        repository.upsertSongs(songs)
    }
}