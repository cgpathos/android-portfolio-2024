package today.pathos.android.portfolio.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import today.pathos.android.portfolio.domain.repository.FameRepository
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.presentation.viewmodel.state.MainEffectProvider
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    mainEffectProvider: MainEffectProvider,
    private val repository: FameRepository,
) : BaseViewModel(mainEffectProvider) {

    private val _state = MutableStateFlow(MainUiState.EMPTY_STATE)
    val state = _state.asStateFlow()

    val newStyleState: StateFlow<MainUiState> =
        repository.getFameCharacterListFlow()
            .flatMapLatest {
                flowOf(
                    MainUiState(
                        fameTop5List = it.take(5),
                        fameList = it.drop(5)
                    )
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = MainUiState.EMPTY_STATE
            )

    init {
        launchWithMainState {
            val fameList = repository.getFameCharacterList()

            _state.update {
                it.copy(
                    fameTop5List = fameList.take(5),
                    fameList = fameList.drop(5)
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
