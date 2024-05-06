package today.pathos.android.portfolio.domain.usecase

import today.pathos.android.portfolio.domain.repository.CacheRepository
import today.pathos.android.portfolio.domain.repository.FameRepository
import javax.inject.Inject

class CleanupCacheUseCase @Inject constructor(
    private val cacheRepository: CacheRepository,
) {

    suspend operator fun invoke() {
        cacheRepository.cleanCache()
    }
}
