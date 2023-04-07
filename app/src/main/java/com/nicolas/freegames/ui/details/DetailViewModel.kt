package com.nicolas.freegames.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.freegames.data.repository.FreeGameRepository
import com.nicolas.freegames.models.domain.DetailGame
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: FreeGameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailGameState())
    val uiState: StateFlow<DetailGameState> = _uiState.asStateFlow()

    fun onEvent(event: DetailGameEvent) {
        when (event) {
            is DetailGameEvent.Refresh -> loadGameData(event.gameId)
        }
    }

    fun loadGameData(gameId: String) = viewModelScope.launch {
        repository.getGameDetail(gameId)
            .onStart { setStateGameUi(isLoading = true) }
            .catch { setStateGameUi(isError = true) }
            .collect { data -> setStateGameUi(data = data) }
    }

    private fun setStateGameUi(
        isLoading: Boolean = false,
        data: DetailGame? = DetailGame(),
        isError: Boolean = false
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = isLoading,
                data = data,
                isError = isError
            )
        }
    }
}