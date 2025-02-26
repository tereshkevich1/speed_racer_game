package com.example.speed_racer.data.di

import com.example.speed_racer.data.repository.GamePreferencesRepositoryImpl
import com.example.speed_racer.domain.repository.GamePreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule{
    @Binds
    fun provideGamePrefRepository(impl: GamePreferencesRepositoryImpl): GamePreferencesRepository
}