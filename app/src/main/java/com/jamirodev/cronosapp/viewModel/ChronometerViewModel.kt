package com.jamirodev.cronosapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jamirodev.cronosapp.state.CronoState
import kotlinx.coroutines.Job

class ChronometerViewModel: ViewModel() {
    var state by mutableStateOf(CronoState())
        private set
    var cronJob by mutableStateOf<Job?>(null)
        private set
    var time by mutableLongStateOf(0L)
        private set

    fun onValue(value: String) {
        state = state.copy(title = value)
    }
}