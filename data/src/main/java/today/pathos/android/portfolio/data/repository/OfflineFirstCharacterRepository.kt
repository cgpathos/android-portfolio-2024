package today.pathos.android.portfolio.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import today.pathos.android.portfolio.common.di.IoDispatcher
import today.pathos.android.portfolio.data.datasource.local.LocalDataSource
import today.pathos.android.portfolio.data.datasource.local.db.table.AvatarTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.CharacterTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.EquipmentTbl
import today.pathos.android.portfolio.data.datasource.remote.NetworkDataSource
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
    override fun getCharacterFlow(serverId: String, characterId: String): Flow<Character> =
        localDataSource.getCharacter(serverId, characterId)
            .onStart {
                if (localDataSource.isCharacterEmpty(serverId, characterId)) {
                    val result = networkDataSource.getCharacter(serverId, characterId)
                    localDataSource.createCharacter(serverId, result)
                }
            }
            .map { it.toEntity() }
            .flowOn(dispatcher)

    override fun getCharacterEquipmentFlow(serverId: String, characterId: String): Flow<List<Equipment>> =
        localDataSource.getCharacterEquipment(serverId, characterId)
            .onStart {
                if (localDataSource.isCharacterEquipmentEmpty(serverId, characterId)) {
                    val result = networkDataSource.getCharacterEquipment(serverId, characterId).equipment
                    localDataSource.createCharacterEquipment(serverId, characterId, result)
                }
            }
            .map { it.toEntity() }
            .flowOn(dispatcher)

    override fun getCharacterAvatarFlow(serverId: String, characterId: String): Flow<List<Avatar>> =
        localDataSource.getCharacterAvatar(serverId, characterId)
            .onStart {
                if (localDataSource.isCharacterAvatarEmpty(serverId, characterId)) {
                    val result = networkDataSource.getCharacterAvatar(serverId, characterId).avatar
                    localDataSource.createCharacterAvatar(serverId, characterId, result)
                }
            }
            .map { it.toEntity() }
            .flowOn(dispatcher)

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
