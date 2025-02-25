package com.example.speed_racer.domain.repository

import kotlinx.coroutines.flow.Flow

interface GamePreferencesRepository {
    fun getBestScore(): Flow<Int?>
    suspend fun saveBestScore(bestScore: Int)
}