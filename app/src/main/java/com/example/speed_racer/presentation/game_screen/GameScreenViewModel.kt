package com.example.speed_racer.presentation.game_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speed_racer.domain.use_case.SaveBestScoreUseCase
import com.example.speed_racer.presentation.game_screen.util.constants.GameConstants.GAME_DURATION_SECONDS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(private val saveBestScoreUseCase: SaveBestScoreUseCase) :
    ViewModel() {
    private val _remainingTime = MutableStateFlow(GAME_DURATION_SECONDS)
    val remainingTime = _remainingTime.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (_remainingTime.value > 0) {
                delay(1000L)
                _remainingTime.value -= 1
            }
        }
    }

    fun incrementScore() {
        _score.value += 1
    }

    fun saveBestScore() {
        viewModelScope.launch {
            saveBestScoreUseCase(score.value)
        }
    }
}