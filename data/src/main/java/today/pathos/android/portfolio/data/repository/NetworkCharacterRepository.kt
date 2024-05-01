package today.pathos.android.portfolio.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import today.pathos.android.portfolio.data.datasource.remote.NetworkDataSource
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResAvatar
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResCharacter
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResEquipment
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResItem
import today.pathos.android.portfolio.domain.di.IoDispatcher
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.entity.Equipment
import today.pathos.android.portfolio.entity.Item
import javax.inject.Inject

class NetworkCharacterRepository @Inject constructor(
    private val dataSource: NetworkDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : today.pathos.android.portfolio.domain.repository.CharacterRepository {
    override suspend fun getCharacter(
        serverId: String,
        characterId: String,
    ): Character = withContext(dispatcher) {
        dataSource.getCharacter(serverId, characterId).toEntity(serverId)
    }

    override suspend fun getCharacterEquipment(
        serverId: String,
        characterId: String,
    ): List<Equipment> = withContext(dispatcher) {
        dataSource.getCharacterEquipment(serverId, characterId).equipment.toEntity()
    }

    override suspend fun getCharacterAvatar(
        serverId: String,
        characterId: String,
    ): List<today.pathos.android.portfolio.entity.Avatar> = withContext(dispatcher) {
        dataSource.getCharacterAvatar(serverId, characterId).avatar.toEntity()
    }

    override suspend fun getItemInfo(itemId: String): Item = withContext(dispatcher) {
        dataSource.getItemInfo(itemId).toEntity()
    }
}

private fun ResCharacter.toEntity(serverId: String) = Character(
    serverId = serverId,
    characterId = characterId,
    characterName = characterName,
    level = level,
    jobId = jobId,
    jobGrowId = jobGrowId,
    jobName = jobName,
    jobGrowName = jobGrowName,
    fame = fame ?: 0,
    adventureName = adventureName,
    guildId = guildId,
    guildName = guildName,
)

private fun ResEquipment.toEntity() = Equipment(
    slotId = slotId,
    slotName = slotName,
    itemId = itemId,
    itemName = itemName,
    itemTypeId = itemTypeId,
    itemType = itemType,
    itemTypeDetailId = itemTypeDetailId,
    itemTypeDetail = itemTypeDetail,
    itemAvailableLevel = itemAvailableLevel,
    itemRarity = itemRarity,
    setItemId = setItemId,
    setItemName = setItemName,
    reinforce = reinforce,
    itemGradeName = itemGradeName,
    amplificationName = amplificationName,
    expiredDate = expiredDate,
    refine = refine,
)

@JvmName("callFromEquipment")
private fun List<ResEquipment>.toEntity() = map { it.toEntity() }

private fun ResAvatar.toEntity() = today.pathos.android.portfolio.entity.Avatar(
    slotId = slotId,
    slotName = slotName,
    itemId = itemId,
    itemName = itemName,
    cloneItemId = clone.itemId,
    cloneItemName = clone.itemName,
    itemRarity = itemRarity,
    optionAbility = optionAbility
)

@JvmName("callFromAvatar")
private fun List<ResAvatar>.toEntity() = map { it.toEntity() }

private fun ResItem.toEntity() = Item(
    itemId = itemId,
    itemName = itemName,
    itemRarity = itemRarity,
    itemTypeId = itemTypeId,
    itemType = itemType,
    itemTypeDetailId = itemTypeDetailId,
    itemTypeDetail = itemTypeDetail,
    itemAvailableLevel = itemAvailableLevel,
    itemExplain = itemExplain,
    itemExplainDetail = itemExplainDetail,
    itemFlavorText = itemFlavorText,
    setItemId = setItemId,
    setItemName = setItemName
)
