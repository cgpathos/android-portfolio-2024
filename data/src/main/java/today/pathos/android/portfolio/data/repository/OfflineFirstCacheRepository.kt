package today.pathos.android.portfolio.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import today.pathos.android.portfolio.common.di.IoDispatcher
import today.pathos.android.portfolio.data.datasource.local.LocalDataSource
import today.pathos.android.portfolio.domain.repository.CacheRepository
import javax.inject.Inject

class OfflineFirstCacheRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : CacheRepository {
    override suspend fun cleanCache() = withContext(dispatcher) {
        localDataSource.cleanCache()
    }
}
