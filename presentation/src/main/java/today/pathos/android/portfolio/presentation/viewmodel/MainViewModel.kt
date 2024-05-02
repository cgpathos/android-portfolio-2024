package today.pathos.android.portfolio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import today.pathos.android.portfolio.common.result.Result
import today.pathos.android.portfolio.common.result.asResult
import today.pathos.android.portfolio.domain.repository.FameRepository
import today.pathos.android.portfolio.entity.Character
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: FameRepository,
) : ViewModel() {

    val state: StateFlow<Result<MainUiState>> = mainUiState(repository)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = Result.Loading
        )
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun mainUiState(
    repository: FameRepository,
): Flow<Result<MainUiState>> {
    return repository.getFameCharacterListFlow()
        .flatMapLatest {
            flowOf(
                MainUiState(
                    fameTop5List = it.take(5),
                    fameList = it.drop(5)
                )
            )
        }.asResult()
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
