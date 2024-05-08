package today.pathos.android.portfolio.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import today.pathos.android.portfolio.common.di.IoDispatcher
import today.pathos.android.portfolio.data.datasource.remote.NetworkDataSource
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResAvatar
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResCharacter
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResEquipment
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResItem
import today.pathos.android.portfolio.domain.repository.CharacterRepository
import today.pathos.android.portfolio.entity.Avatar
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.entity.Equipment
import today.pathos.android.portfolio.entity.Item
import javax.inject.Inject

class NetworkCharacterRepository @Inject constructor(
    private val dataSource: NetworkDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : CharacterRepository {
    override fun getCharacterFlow(
        serverId: String,
        characterId: String,
    ): Flow<Character> =
        flow {
            emit(dataSource.getCharacter(serverId, characterId).toEntity(serverId))
        }.flowOn(dispatcher)

    override fun getCharacterEquipmentFlow(
        serverId: String,
        characterId: String,
    ): Flow<List<Equipment>> =
        flow {
            emit(dataSource.getCharacterEquipment(serverId, characterId).equipment.toEntity())
        }.flowOn(dispatcher)

    override fun getCharacterAvatarFlow(
        serverId: String,
        characterId: String,
    ): Flow<List<Avatar>> =
        flow {
            emit(dataSource.getCharacterAvatar(serverId, characterId).avatar.toEntity())
        }.flowOn(dispatcher)

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
