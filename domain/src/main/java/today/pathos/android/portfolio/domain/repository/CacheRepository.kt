package today.pathos.android.portfolio.domain.repository

interface CacheRepository {
    suspend fun cleanCache()
}
