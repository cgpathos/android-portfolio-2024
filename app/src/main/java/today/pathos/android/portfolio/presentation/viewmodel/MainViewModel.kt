package today.pathos.android.portfolio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import today.pathos.android.portfolio.domain.repository.FameRepository
import today.pathos.android.portfolio.entity.Character
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FameRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState.EMPTY_STATE)
    val state = _state
        .onSubscription { initMain() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainUiState.EMPTY_STATE
        )

    private fun initMain() {
        viewModelScope.launch {
            val fameTop5List = repository.getTop5Fame()
            val fameList = repository.getFameCharacterList()

            _state.update {
                it.copy(
                    fameTop5List = fameTop5List,
                    fameList = fameList
                )
            }
        }
    }
}

data class MainUiState(
    val fameTop5List: List<Character> = emptyList(),
    val fameList: List<Character> = emptyList(),
) {
    companion object {
        val EMPTY_STATE = MainUiState(
            fameTop5List = emptyList(),
            fameList = emptyList()
        )
    }
}
