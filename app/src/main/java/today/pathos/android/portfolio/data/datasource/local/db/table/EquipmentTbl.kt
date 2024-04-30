package today.pathos.android.portfolio.data.datasource.local.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import today.pathos.android.portfolio.entity.EquipmentSlotId

@Entity(
    tableName = "EQUIPMENT_TBL",
    primaryKeys = ["server_id", "character_id", "item_id"]
)
data class EquipmentTbl(
    @ColumnInfo(name = "server_id") val serverId: String,
    @ColumnInfo(name = "character_id") val characterId: String,
    @ColumnInfo(name = "item_id") val itemId: String,
    @ColumnInfo(name = "item_name") val itemName: String,
    @ColumnInfo(name = "slot_id") val slotId: EquipmentSlotId,
    @ColumnInfo(name = "slot_name") val slotName: String,
    @ColumnInfo(name = "item_type_id") val itemTypeId: String,
    @ColumnInfo(name = "item_type") val itemType: String,
    @ColumnInfo(name = "item_type_detail_id") val itemTypeDetailId: String,
    @ColumnInfo(name = "item_type_detail") val itemTypeDetail: String,
    @ColumnInfo(name = "item_available_level") val itemAvailableLevel: Int,
    @ColumnInfo(name = "item_rarity") val itemRarity: String,
    @ColumnInfo(name = "set_item_id") val setItemId: String? = null,
    @ColumnInfo(name = "set_item_name") val setItemName: String? = null,
    @ColumnInfo(name = "reinforce") val reinforce: Int,
    @ColumnInfo(name = "item_grade_name") val itemGradeName: String? = null,
    @ColumnInfo(name = "amplification_name") val amplificationName: String? = null,
    @ColumnInfo(name = "expired_date") val expiredDate: Long? = null,
    @ColumnInfo(name = "refine") val refine: Int,
)
