package today.pathos.android.portfolio.data.datasource.remote.dto.res

import kotlinx.serialization.Serializable

@Serializable
data class ResEquipment(
    val slotId: EquipmentSlotId,
    val slotName: String,
    val itemId: String,
    val itemName: String,
    val itemTypeId: String,
    val itemType: String,
    val itemTypeDetailId: String,
    val itemTypeDetail: String,
    val itemAvailableLevel: Int,
    val itemRarity: String,
    val setItemId: String? = null,
    val setItemName: String? = null,
    val reinforce: Int,
    val itemGradeName: String? = null,
    val amplificationName: String? = null,
    val expiredDate: Long? = null,
    val refine: Int,
)

enum class EquipmentSlotId {
    WEAPON,
    TITLE,
    JACKET,
    SHOULDER,
    PANTS,
    SHOES,
    WAIST,
    AMULET,
    WRIST,
    RING,
    SUPPORT,
    MAGIC_STON,
    EARRING,
}
