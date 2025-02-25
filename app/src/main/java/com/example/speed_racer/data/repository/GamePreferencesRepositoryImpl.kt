package com.example.speed_racer.data.repository

import com.example.speed_racer.data.data_source.GamePreferencesDataSource
import com.example.speed_racer.domain.repository.GamePreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GamePreferencesRepositoryImpl @Inject constructor(
    private val dataSource: GamePreferencesDataSource
) : GamePreferencesRepository {

    companion object {
        private const val BEST_SCORE_KEY = "best_score"
    }

    override fun getBestScore(): Flow<Int?> = dataSource.getIntValue(BEST_SCORE_KEY)

    override suspend fun saveBestScore(bestScore: Int) {
        dataSource.saveIntValue(BEST_SCORE_KEY, bestScore)
    }
}