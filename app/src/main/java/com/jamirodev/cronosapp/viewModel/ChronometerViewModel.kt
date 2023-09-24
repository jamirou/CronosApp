package com.jamirodev.cronosapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamirodev.cronosapp.repository.CronosRepository
import com.jamirodev.cronosapp.state.CronoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChronometerViewModel @Inject constructor(private val repository: CronosRepository) : ViewModel() {
    var state by mutableStateOf(CronoState())
        private set
    var cronJob by mutableStateOf<Job?>(null)
        private set
    var time by mutableLongStateOf(0L)
        private set
    fun getCronoById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCronoById(id).collect {item ->
                time = item.crono
                state = state.copy(title = item.title)
            }
        }
    }

    fun onValue(value: String) {
        state = state.copy(title = value)
    }

    fun init() {
        state = state.copy(
            chronometerActive = true
        )
    }
    fun pause() {
        state = state.copy(
            chronometerActive = false,
            showSaveButton = true
        )
    }
    fun stop() {
        cronJob?.cancel()
        time = 0
        state = state.copy(
            chronometerActive = false,
            showSaveButton = false,
            showTextField = false
        )
    }
    fun showTextField() {
        state = state.copy(
            showTextField = true
        )
    }
    fun chronos() {
        if (state.chronometerActive) {
            cronJob?.cancel()
            cronJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    time += 1000
                }
            }
        }else{
            cronJob?.cancel()
        }
    }

}



