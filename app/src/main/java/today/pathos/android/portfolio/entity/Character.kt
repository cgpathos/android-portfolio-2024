package today.pathos.android.portfolio.entity

data class Character(
    val serverId: String,
    val characterId: String,
    val characterName: String,
    val level: Int,
    val jobId: String,
    val jobGrowId: String,
    val jobName: String,
    val jobGrowName: String,
    val fame: Int? = null,
    val adventureName: String? = null,
    val guildId: String? = null,
    val guildName: String? = null,
)
