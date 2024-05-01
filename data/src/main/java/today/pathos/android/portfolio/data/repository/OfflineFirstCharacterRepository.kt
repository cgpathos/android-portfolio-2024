package today.pathos.android.portfolio.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import today.pathos.android.portfolio.data.datasource.local.LocalDataSource
import today.pathos.android.portfolio.data.datasource.local.db.table.AvatarTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.CharacterTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.EquipmentTbl
import today.pathos.android.portfolio.data.datasource.remote.NetworkDataSource
import today.pathos.android.portfolio.data.di.IoDispatcher
import today.pathos.android.portfolio.domain.repository.CharacterRepository
import today.pathos.android.portfolio.entity.Avatar
import today.pathos.android.portfolio.entity.Character
import today.pathos.android.portfolio.entity.Equipment
import today.pathos.android.portfolio.entity.Item
import javax.inject.Inject

class OfflineFirstCharacterRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : CharacterRepository {
    override suspend fun getCharacter(
        serverId: String,
        characterId: String,
    ): Character = withContext(dispatcher) {
        if (localDataSource.isCharacterEmpty(serverId, characterId)) {
            val result = networkDataSource.getCharacter(serverId, characterId)
            localDataSource.createCharacter(serverId, result)

        }

        localDataSource.getCharacter(serverId, characterId).toEntity()
    }

    override suspend fun getCharacterEquipment(
        serverId: String,
        characterId: String,
    ): List<Equipment> = withContext(dispatcher) {
        if (localDataSource.isCharacterEquipmentEmpty(serverId, characterId)) {
            val result = networkDataSource.getCharacterEquipment(serverId, characterId).equipment
            localDataSource.createCharacterEquipment(serverId, characterId, result)
        }

        localDataSource.getCharacterEquipment(serverId, characterId).toEntity()
    }

    override suspend fun getCharacterAvatar(
        serverId: String,
        characterId: String,
    ): List<Avatar> = withContext(dispatcher) {
        if (localDataSource.isCharacterAvatarEmpty(serverId, characterId)) {
            val result = networkDataSource.getCharacterAvatar(serverId, characterId).avatar
            localDataSource.createCharacterAvatar(serverId, characterId, result)
        }

        localDataSource.getCharacterAvatar(serverId, characterId).toEntity()
    }

    override suspend fun getItemInfo(itemId: String): Item = withContext(dispatcher) {
        TODO()
    }
}

private fun CharacterTbl.toEntity() = Character(
    serverId = serverId,
    characterId = characterId,
    characterName = characterName,
    level = level,
    jobId = jobId,
    jobGrowId = jobGrowId,
    jobName = jobName,
    jobGrowName = jobGrowName,
    fame = fame,
    adventureName = adventureName,
    guildId = guildId,
    guildName = guildName,
)

private fun EquipmentTbl.toEntity() = Equipment(
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
private fun List<EquipmentTbl>.toEntity() = map { it.toEntity() }

private fun AvatarTbl.toEntity() = Avatar(
    slotId = slotId,
    slotName = slotName,
    itemId = itemId,
    itemName = itemName,
    cloneItemId = cloneItemId,
    cloneItemName = cloneItemName,
    itemRarity = itemRarity,
    optionAbility = optionAbility
)

@JvmName("callFromAvatar")
private fun List<AvatarTbl>.toEntity() = map { it.toEntity() }
