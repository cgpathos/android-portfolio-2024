package today.pathos.android.portfolio.domain.repository

import kotlinx.coroutines.flow.Flow
import today.pathos.android.portfolio.entity.Avatar
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.entity.Equipment

interface CharacterRepository {
    fun getCharacterFlow(
        serverId: String,
        characterId: String,
    ): Flow<Character>

    fun getCharacterEquipmentFlow(
        serverId: String,
        characterId: String,
    ): Flow<List<Equipment>>

    fun getCharacterAvatarFlow(
        serverId: String,
        characterId: String,
    ): Flow<List<Avatar>>

    suspend fun getItemInfo(
        itemId: String,
    ): Any
}
