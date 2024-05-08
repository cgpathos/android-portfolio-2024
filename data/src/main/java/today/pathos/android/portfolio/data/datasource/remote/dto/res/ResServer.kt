package today.pathos.android.portfolio.data.datasource.remote.dto.res

import kotlinx.serialization.Serializable

@Serializable
data class ResServer(
    val serverId: String,
    val serverName: String,
)
