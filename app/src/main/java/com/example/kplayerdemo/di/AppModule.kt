package com.example.kplayerdemo.di

import android.app.Application
import androidx.room.Room
import com.example.kplayerdemo.data.local.SongDatabase
import com.example.kplayerdemo.data.remote.SearchAPI
import com.example.kplayerdemo.data.repository.SongRepositoryImpl
import com.example.kplayerdemo.domain.repository.SongRepository
import com.example.kplayerdemo.util.ConnectivityObserver
import com.example.kplayerdemo.util.ConnectivityObserverImpl
import com.example.kplayerdemo.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSearchAPI(): SearchAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideSongDatabase(app: Application): SongDatabase {
        return Room.databaseBuilder(
            app,
            SongDatabase::class.java,
            SongDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSongRepository(api: SearchAPI, db: SongDatabase): SongRepository {
        return SongRepositoryImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(app: Application): ConnectivityObserver {
        return ConnectivityObserverImpl(app)
    }
}