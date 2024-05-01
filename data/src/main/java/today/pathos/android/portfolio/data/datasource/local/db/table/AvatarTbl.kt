package today.pathos.android.portfolio.data.datasource.local.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import today.pathos.android.portfolio.entity.AvatarSlotId

@Entity(
    tableName = "AVATAR_TBL",
    primaryKeys = ["server_id", "character_id", "item_id"]
)
data class AvatarTbl(
    @ColumnInfo(name = "server_id") val serverId: String,
    @ColumnInfo(name = "character_id") val characterId: String,
    @ColumnInfo(name = "item_id") val itemId: String,
    @ColumnInfo(name = "item_name") val itemName: String,
    @ColumnInfo(name = "slot_id") val slotId: AvatarSlotId,
    @ColumnInfo(name = "slot_name") val slotName: String,
    @ColumnInfo(name = "clone_item_id") val cloneItemId: String?,
    @ColumnInfo(name = "clone_item_name") val cloneItemName: String?,
    @ColumnInfo(name = "item_rarity") val itemRarity: String,
    @ColumnInfo(name = "option_ability") val optionAbility: String?,
)
