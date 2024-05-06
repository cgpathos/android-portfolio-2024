package today.pathos.android.portfolio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import today.pathos.android.portfolio.domain.usecase.CleanupCacheUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val cleanupCacheUseCase: CleanupCacheUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<SplashUiState>(SplashUiState.Init)
    val state: StateFlow<SplashUiState> = _state
        .onStart {
            initApp()
        }
        .catch { e ->
            _state.update { SplashUiState.Error(e.message.toString()) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SplashUiState.Init
        )

    private fun initApp() {
        viewModelScope.launch {
            cleanupCacheUseCase()
            delay(2000) // 스플래시 화면을 잠시 보여주기 위한 딜레이

            _state.update { SplashUiState.NavigateToMain }
        }
    }
}


sealed class SplashUiState {
    data object Init : SplashUiState()
    data class Error(val msg: String) : SplashUiState()
    data object NavigateToMain : SplashUiState()
}
