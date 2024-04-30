package today.pathos.android.portfolio.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import today.pathos.android.portfolio.data.datasource.local.db.table.AvatarTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.CharacterTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.EquipmentTbl

@Dao
abstract class CharacterDao {
    @Insert
    abstract suspend fun createCharacter(character: CharacterTbl)

    @Query("SELECT 1 FROM CHARACTER_TBL WHERE server_id = :serverId and character_id = :characterId")
    abstract suspend fun hasCharacter(
        serverId: String,
        characterId: String,
    ): Boolean

    @Query("SELECT * FROM CHARACTER_TBL WHERE server_id = :serverId and character_id = :characterId")
    abstract suspend fun getCharacter(
        serverId: String,
        characterId: String,
    ): CharacterTbl

    @Upsert
    abstract suspend fun upsertEquipment(equipmentList: List<EquipmentTbl>)

    @Query("SELECT count(*) FROM EQUIPMENT_TBL WHERE server_id = :serverId and character_id = :characterId")
    abstract suspend fun hasCharacterEquipment(
        serverId: String,
        characterId: String,
    ): Boolean

    @Query("SELECT * FROM EQUIPMENT_TBL WHERE server_id = :serverId and character_id = :characterId")
    abstract suspend fun getCharacterEquipment(
        serverId: String,
        characterId: String,
    ): List<EquipmentTbl>

    @Upsert
    abstract suspend fun upsertAvatar(equipmentList: List<AvatarTbl>)

    @Query("SELECT count(*) FROM AVATAR_TBL WHERE server_id = :serverId and character_id = :characterId")
    abstract suspend fun hasCharacterAvatar(
        serverId: String,
        characterId: String,
    ): Boolean

    @Query("SELECT * FROM AVATAR_TBL WHERE server_id = :serverId and character_id = :characterId")
    abstract suspend fun getCharacterAvatar(
        serverId: String,
        characterId: String,
    ): List<AvatarTbl>
}
