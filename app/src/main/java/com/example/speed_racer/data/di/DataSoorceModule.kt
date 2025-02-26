package com.example.speed_racer.data.di

import com.example.speed_racer.data.data_source.GamePreferencesDataSource
import com.example.speed_racer.data.data_source.GamePreferencesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun provideGamePrefsDataSource(impl: GamePreferencesDataSourceImpl): GamePreferencesDataSource
}

