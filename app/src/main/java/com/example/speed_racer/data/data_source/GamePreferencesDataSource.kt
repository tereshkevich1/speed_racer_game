package com.example.speed_racer.data.data_source

import kotlinx.coroutines.flow.Flow

interface GamePreferencesDataSource {
    fun getIntValue(key: String): Flow<Int?>
    suspend fun saveIntValue(key: String, value: Int)
}