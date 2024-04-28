package today.pathos.android.portfolio.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import today.pathos.android.portfolio.data.repository.NetworkFameRepository
import today.pathos.android.portfolio.domain.repository.FameRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Binds
    abstract fun bindsFameRepository(
        fameRepository: NetworkFameRepository,
    ): FameRepository

}
