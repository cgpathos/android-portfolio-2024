package today.pathos.android.portfolio.data.datasource.remote.dto.res

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Objects

@Serializable
data class ResItem(
    val itemId: String,
    val itemName: String,
    val itemRarity: String,
    val itemTypeId: String,
    val itemType: String,
    val itemTypeDetailId: String,
    val itemTypeDetail: String,
    val itemAvailableLevel: Int,
    val itemExplain: String,
    val itemExplainDetail: String,
    val itemFlavorText: String,
    val setItemId: String?,
    val setItemName: String?,
    val itemStatus: List<ResItemStatus>,
)

@Serializable
data class ResItemStatus(
    val name: String,
    @Contextual val value: String,
)
