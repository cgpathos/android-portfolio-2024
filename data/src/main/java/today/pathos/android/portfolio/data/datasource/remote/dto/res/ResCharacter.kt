package today.pathos.android.portfolio.data.datasource.remote.dto.res

import kotlinx.serialization.Serializable

@Serializable
data class ResCharacter(
    val serverId: String? = null,
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
    val equipment: List<ResEquipment> = emptyList(),
    val avatar: List<ResAvatar> = emptyList(),
)
