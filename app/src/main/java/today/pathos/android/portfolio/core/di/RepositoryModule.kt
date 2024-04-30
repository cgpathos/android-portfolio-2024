package today.pathos.android.portfolio.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import today.pathos.android.portfolio.data.repository.OfflineFirstCharacterRepository
import today.pathos.android.portfolio.data.repository.OfflineFirstFameRepository
import today.pathos.android.portfolio.domain.repository.CharacterRepository
import today.pathos.android.portfolio.domain.repository.FameRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindsFameRepository(
        fameRepository: OfflineFirstFameRepository,
    ): FameRepository

    @Binds
    abstract fun bindsCharacterRepository(
        characterRepository: OfflineFirstCharacterRepository,
    ): CharacterRepository
}
