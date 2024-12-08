package com.example.kplayerdemo.domain.use_case

import com.example.kplayerdemo.data.remote.dto.SongListResponse
import com.example.kplayerdemo.data.remote.dto.toSong
import com.example.kplayerdemo.domain.model.Song
import com.example.kplayerdemo.domain.repository.SongRepository
import com.example.kplayerdemo.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchSongsUseCase @Inject constructor(
    private val repository: SongRepository
) {
    operator fun invoke(artistName: String): Flow<Resource<List<Song>>> = flow {
        try {
            emit(Resource.Loading())
            val songs = repository.searchSongsByArtistName(artistName)
            emit(Resource.Success(songs))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}