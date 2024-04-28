package today.pathos.android.portfolio.data.datasource.remote.dto.res

import kotlinx.serialization.Serializable

@Serializable
data class ResError(
    val error: ResErrorInfo,
)

@Serializable
data class ResErrorInfo(
    val status: Int,
    val code: String,
    val message: String,
)
