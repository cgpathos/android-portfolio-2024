package today.pathos.android.portfolio.entity

import kotlinx.serialization.Serializable
import today.pathos.android.portfolio.entity.AvatarSlotId

data class Avatar(
    val slotId: AvatarSlotId,
    val slotName: String,
    val itemId: String,
    val itemName: String,
    val cloneItemId: String?,
    val cloneItemName: String?,
    val itemRarity: String,
    val optionAbility: String?,
)
