package today.pathos.android.portfolio.entity

import today.pathos.android.portfolio.BuildConfig

data class Item(
    val itemId: String,
    val itemName: String,
    val itemImage: String,
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
) {

    companion object {
        fun getItemImageUrl(
            itemId: String,
        ): String =
            BuildConfig.ITEM_IMAGE_URL.format(itemId)
    }
}

