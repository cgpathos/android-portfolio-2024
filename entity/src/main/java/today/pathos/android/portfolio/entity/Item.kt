package today.pathos.android.portfolio.entity

data class Item(
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
)

