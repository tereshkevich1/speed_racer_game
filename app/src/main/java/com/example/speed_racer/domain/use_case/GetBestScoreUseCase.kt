package com.example.speed_racer.domain.use_case

import com.example.speed_racer.domain.repository.GamePreferencesRepository
import javax.inject.Inject

class GetBestScoreUseCase @Inject constructor(private val gamePreferencesRepository: GamePreferencesRepository) {
    operator fun invoke() = gamePreferencesRepository.getBestScore()
}

