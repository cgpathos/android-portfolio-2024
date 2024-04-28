package today.pathos.android.portfolio.data.datasource.remote.dto.res

import kotlinx.serialization.Serializable

@Serializable
data class ResRows<T>(
    val rows: List<T>,
)
