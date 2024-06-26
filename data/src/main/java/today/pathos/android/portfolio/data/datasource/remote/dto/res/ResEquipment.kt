package today.pathos.android.portfolio.data.datasource.remote.dto.res

import kotlinx.serialization.Serializable
import today.pathos.android.portfolio.entity.EquipmentSlotId

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
