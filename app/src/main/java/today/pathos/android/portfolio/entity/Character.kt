package today.pathos.android.portfolio.entity

import today.pathos.android.portfolio.BuildConfig

data class Character(
    val serverId: String,
    val characterId: String,
    val characterName: String,
    val characterImage: String,
    val level: Int,
    val jobId: String,
    val jobGrowId: String,
    val jobName: String,
    val jobGrowName: String,
    val fame: Int = 0,
    val adventureName: String? = null,
    val guildId: String? = null,
    val guildName: String? = null,
    val equipment: List<Equipment> = emptyList(),
    val avatar: List<Avatar> = emptyList()
) {

    companion object {
        fun getCharacterImageUrl(
            serverId: String,
            characterId: String,
            zoom: Int,
        ): String =
            BuildConfig.CHARACTER_IMAGE_URL.format(serverId, characterId, zoom)
    }
}

