package today.pathos.android.portfolio.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
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
import today.pathos.android.portfolio.domain.usecase.GetCharacterInfoFlowUseCase
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.presentation.viewmodel.state.ActionEffect
import today.pathos.android.portfolio.presentation.viewmodel.state.ActionEffectProvider
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    savedState: SavedStateHandle,
    actionEffectProvider: ActionEffectProvider,
    getCharacterInfoFlowUseCase: GetCharacterInfoFlowUseCase,
) : ViewModel() {
    private val serverId: String = checkNotNull(savedState["serverId"])
    private val characterId: String = checkNotNull(savedState["characterId"])

    val state: StateFlow<Result<CharacterInfoUiState>> =
        characterInfoUiState(
            serverId,
            characterId,
            getCharacterInfoFlowUseCase,
            actionEffectProvider
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = Result.Loading
        )
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun characterInfoUiState(
    serverId: String,
    characterId: String,
    getCharacterInfoFlowUseCase: GetCharacterInfoFlowUseCase,
    actionEffectProvider: ActionEffectProvider,
): Flow<Result<CharacterInfoUiState>> =
    getCharacterInfoFlowUseCase(serverId, characterId)
        .flatMapLatest { characterInfo ->
            val armorList = characterInfo.equipment
                .filter { it.itemType == "방어구" }
                .map { equipment ->
                    ItemSlotInfo(
                        itemId = equipment.itemId,
                        reinforce = equipment.reinforce.takeIf { it > 0 }
                    )
                }
            val accessoryList = characterInfo.equipment
                .filter { it.itemType in listOf("무기", "액세서리", "추가장비") }
                .map { equipment ->
                    ItemSlotInfo(
                        itemId = equipment.itemId,
                        reinforce = equipment.reinforce.takeIf { it > 0 }
                    )
                }
            flowOf(
                CharacterInfoUiState(
                    characterInfo = characterInfo,
                    armorList = armorList,
                    accessoryList = accessoryList
                )
            )
        }.asResult(
            errorCallback = {
                actionEffectProvider.tryAction(
                    ActionEffect.NavigateTo(
                        postDest = Screens.NavigateUp
                    )
                )
            }
        )

data class CharacterInfoUiState(
    val characterInfo: Character,
    val armorList: List<ItemSlotInfo> = emptyList(),
    val accessoryList: List<ItemSlotInfo> = emptyList(),
) {
    companion object {
        val EMPTY_STATE = CharacterInfoUiState(
            characterInfo = Character(
                serverId = "",
                characterId = "",
                characterName = "",
                level = 0,
                jobId = "",
                jobGrowId = "",
                jobName = "",
                jobGrowName = "",
                fame = 0,
                adventureName = "",
                guildId = "",
                guildName = "",
            )
        )
    }
}

data class ItemSlotInfo(
    val itemId: String,
    val reinforce: Int?,
)
