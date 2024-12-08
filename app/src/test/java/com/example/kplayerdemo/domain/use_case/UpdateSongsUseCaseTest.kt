package com.example.kplayerdemo.domain.use_case

import com.example.kplayerdemo.data.repository.FakeSongRepository
import com.example.kplayerdemo.util.DummySongUtil
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateSongsUseCaseTest {
    private lateinit var updateSongsUseCase: UpdateSongsUseCase
    private lateinit var fakeSongRepository: FakeSongRepository

    @Before
    fun setUp() {
        fakeSongRepository = FakeSongRepository()
        updateSongsUseCase = UpdateSongsUseCase(fakeSongRepository)
    }

    @Test
    fun `Update songs, expect getting only prepared result`() = runBlocking {
        val songsForUpdate = DummySongUtil.prepareDummyData()
        updateSongsUseCase(songsForUpdate)
        val currentSongs = fakeSongRepository.getAllSongs()
        assert(songsForUpdate == currentSongs)
    }
}