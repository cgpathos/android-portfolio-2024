package today.pathos.android.portfolio.data.datasource.local

import today.pathos.android.portfolio.data.datasource.local.db.dao.CharacterDao
import today.pathos.android.portfolio.data.datasource.local.db.dao.FameDao
import today.pathos.android.portfolio.data.datasource.local.db.table.AvatarTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.CharacterTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.EquipmentTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.FameTbl
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResAvatar
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResCharacter
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResEquipment
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val fameDao: FameDao,
    private val characterDao: CharacterDao,
) {
    suspend fun isFameListEmpty(): Boolean =
        fameDao.fameListCount() == 0

    suspend fun getFameList(): List<FameTbl> =
        fameDao.getFameList()

    suspend fun createFameList(characterList: List<ResCharacter>) {
        fameDao.createFame(
            fameList = characterList.map {
                FameTbl(
                    serverId = checkNotNull(it.serverId),
                    characterId = it.characterId,
                    characterName = it.characterName,
                    level = it.level,
                    jobId = it.jobId,
                    jobGrowId = it.jobGrowId,
                    jobName = it.jobName,
                    jobGrowName = it.jobGrowName,
                    fame = it.fame ?: 0,
                )
            }
        )
    }

    suspend fun isCharacterEmpty(
        serverId: String,
        characterId: String,
    ): Boolean = characterDao.hasCharacter(serverId, characterId).not()

    suspend fun getCharacter(
        serverId: String,
        characterId: String,
    ): CharacterTbl = characterDao.getCharacter(serverId, characterId)

    suspend fun createCharacter(
        serverId: String,
        character: ResCharacter,
    ) {
        characterDao.createCharacter(
            CharacterTbl(
                serverId = serverId,
                characterId = character.characterId,
                characterName = character.characterName,
                level = character.level,
                jobId = character.jobId,
                jobGrowId = character.jobGrowId,
                jobName = character.jobName,
                jobGrowName = character.jobGrowName,
                fame = character.fame ?: 0,
            )
        )
    }

    suspend fun isCharacterEquipmentEmpty(
        serverId: String,
        characterId: String,
    ): Boolean = characterDao.hasCharacterEquipment(serverId, characterId).not()

    suspend fun getCharacterEquipment(
        serverId: String,
        characterId: String,
    ): List<EquipmentTbl> = characterDao.getCharacterEquipment(serverId, characterId)

    suspend fun createCharacterEquipment(
        serverId: String,
        characterId: String,
        equipmentList: List<ResEquipment>,
    ) {
        characterDao.upsertEquipment(
            equipmentList.map {
                EquipmentTbl(
                    serverId = serverId,
                    characterId = characterId,
                    slotId = it.slotId,
                    slotName = it.slotName,
                    itemId = it.itemId,
                    itemName = it.itemName,
                    itemTypeId = it.itemTypeId,
                    itemType = it.itemType,
                    itemTypeDetailId = it.itemTypeDetailId,
                    itemTypeDetail = it.itemTypeDetail,
                    itemAvailableLevel = it.itemAvailableLevel,
                    itemRarity = it.itemRarity,
                    setItemId = it.setItemId,
                    setItemName = it.setItemName,
                    reinforce = it.reinforce,
                    itemGradeName = it.itemGradeName,
                    amplificationName = it.amplificationName,
                    expiredDate = it.expiredDate,
                    refine = it.refine,
                )
            }
        )
    }

    suspend fun isCharacterAvatarEmpty(
        serverId: String,
        characterId: String,
    ): Boolean = characterDao.hasCharacterAvatar(serverId, characterId).not()

    suspend fun getCharacterAvatar(
        serverId: String,
        characterId: String,
    ): List<AvatarTbl> = characterDao.getCharacterAvatar(serverId, characterId)

    suspend fun createCharacterAvatar(
        serverId: String,
        characterId: String,
        equipmentList: List<ResAvatar>,
    ) {
        characterDao.upsertAvatar(
            equipmentList.map {
                AvatarTbl(
                    serverId = serverId,
                    characterId = characterId,
                    slotId = it.slotId,
                    slotName = it.slotName,
                    itemId = it.itemId,
                    itemName = it.itemName,
                    cloneItemId = it.clone.itemId,
                    cloneItemName = it.clone.itemName,
                    itemRarity = it.itemRarity,
                    optionAbility = it.optionAbility
                )
            }
        )
    }
}
