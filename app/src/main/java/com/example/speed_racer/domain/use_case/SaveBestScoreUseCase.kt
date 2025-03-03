package com.example.speed_racer.domain.use_case

import com.example.speed_racer.domain.repository.GamePreferencesRepository
import javax.inject.Inject

class SaveBestScoreUseCase @Inject constructor(private val gamePreferencesRepository: GamePreferencesRepository) {
    suspend operator fun invoke(value: Int) {
        gamePreferencesRepository.saveBestScore(value)
    }
}