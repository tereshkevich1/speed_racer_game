package com.example.speed_racer.presentation.start_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speed_racer.domain.use_case.GetBestScoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(getBestScoreUseCase: GetBestScoreUseCase) :
    ViewModel() {
    private val _bestScore = MutableStateFlow("0")
    val bestScore = _bestScore.asStateFlow()

    init {
        Log.d("s2s2",this.toString())
        viewModelScope.launch {
            getBestScoreUseCase().collect { score ->
                score?.let {
                    _bestScore.value = it.toString()
                }
            }
        }
    }
}