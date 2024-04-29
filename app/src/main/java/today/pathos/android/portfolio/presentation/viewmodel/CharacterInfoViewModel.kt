package today.pathos.android.portfolio.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import today.pathos.android.portfolio.domain.usecase.GetCharacterInfoUseCase
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.entity.Item
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase,
) : ViewModel() {
    private val serverId: String = checkNotNull(savedState["serverId"])
    private val characterId: String = checkNotNull(savedState["characterId"])

    private val _state = MutableStateFlow(CharacterInfoUiState.EMPTY_STATE)
    val state = _state
        .onSubscription { initInfo() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CharacterInfoUiState.EMPTY_STATE
        )

    private fun initInfo() {
        Log.i("TAG", "[$serverId]:$characterId ")
        viewModelScope.launch {
            val characterInfo = getCharacterInfoUseCase(serverId, characterId)
            val armorList = characterInfo.equipment
                .filter { it.itemType == "방어구" }
                .map { equipment ->
                    ItemSlotInfo(
                        itemId = equipment.itemId,
                        itemImage = Item.getItemImageUrl(equipment.itemId),
                        reinforce = equipment.reinforce.takeIf { it > 0 }
                    )
                }
            val accessoryList = characterInfo.equipment
                .filter { it.itemType in listOf("무기","액세서리", "추가장비") }
                .map { equipment ->
                    ItemSlotInfo(
                        itemId = equipment.itemId,
                        itemImage = Item.getItemImageUrl(equipment.itemId),
                        reinforce = equipment.reinforce.takeIf { it > 0 }
                    )
                }

            _state.update {
                it.copy(
                    characterInfo = characterInfo,
                    armorList = armorList,
                    accessoryList = accessoryList
                )
            }
        }
    }
}

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
                characterImage = "",
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
    val itemImage: String,
    val reinforce: Int?,
)
