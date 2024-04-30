package today.pathos.android.portfolio.domain.repository

import today.pathos.android.portfolio.entity.Avatar
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.entity.Equipment

interface CharacterRepository {
    suspend fun getCharacter(
        serverId: String,
        characterId: String,
    ): Character

    suspend fun getCharacterEquipment(
        serverId: String,
        characterId: String,
    ): List<Equipment>

    suspend fun getCharacterAvatar(
        serverId: String,
        characterId: String,
    ): List<Avatar>

    suspend fun getItemInfo(
        itemId: String,
    ): Any
}
