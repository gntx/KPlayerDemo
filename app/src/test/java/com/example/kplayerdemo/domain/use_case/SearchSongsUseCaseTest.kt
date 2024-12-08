package com.example.kplayerdemo.domain.use_case

import com.example.kplayerdemo.data.repository.FakeSongRepository
import com.example.kplayerdemo.util.DummySongUtil
import com.example.kplayerdemo.util.Resource
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchSongsUseCaseTest {
    private lateinit var searchSongsUseCase: SearchSongsUseCase
    private lateinit var fakeSongRepository: FakeSongRepository

    @Before
    fun setUp() {
        fakeSongRepository = FakeSongRepository()
        searchSongsUseCase = SearchSongsUseCase(fakeSongRepository)
    }

    @Test
    fun `Search songs, get isLoading firstly, then dummy data successfully`() = runBlocking {
        val outputFlow = searchSongsUseCase("dummy")
        val firstState = outputFlow.first()
        assert(firstState is Resource.Loading)
        val secondState = outputFlow.drop(1).first()
        assert(secondState is Resource.Success)
        assert(secondState.data == DummySongUtil.prepareDummyData())
    }

}