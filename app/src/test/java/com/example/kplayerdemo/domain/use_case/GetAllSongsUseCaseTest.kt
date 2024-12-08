package com.example.kplayerdemo.domain.use_case

import com.example.kplayerdemo.data.repository.FakeSongRepository
import com.example.kplayerdemo.util.DummySongUtil
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllSongsUseCaseTest {
    private lateinit var getAllSongsUseCase: GetAllSongsUseCase
    private lateinit var fakeSongRepository: FakeSongRepository

    @Before
    fun setUp() {
        fakeSongRepository = FakeSongRepository()
        getAllSongsUseCase = GetAllSongsUseCase(fakeSongRepository)
    }

    @Test
    fun `Get all prepared results`() = runBlocking {
        val songs = getAllSongsUseCase()
        assert(songs == DummySongUtil.prepareDummyData())
    }
}