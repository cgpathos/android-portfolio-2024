package today.pathos.android.portfolio.entity

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
