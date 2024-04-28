package today.pathos.android.portfolio.data.datasource.remote.dto.res

import kotlinx.serialization.Serializable

@Serializable
data class ResAvatar(
    val slotId: AvatarSlotId,
    val slotName: String,
    val itemId: String,
    val itemName: String,
    val itemRarity: String,
    val clone: ResClone,
    val optionAbility: String,
)

@Serializable
data class ResClone(
    val itemId: String?,
    val itemName: String?,
)

enum class AvatarSlotId {
    HEADGEAR,
    HAIR,
    FACE,
    JACKET,
    PANTS,
    SHOES,
    BREAST,
    WAIST,
    SKIN,
}
