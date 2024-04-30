package today.pathos.android.portfolio.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import today.pathos.android.portfolio.data.datasource.local.db.CacheDatabase
import today.pathos.android.portfolio.data.datasource.local.db.dao.CharacterDao
import today.pathos.android.portfolio.data.datasource.local.db.dao.FameDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): CacheDatabase =
        Room.databaseBuilder(
            context,
            CacheDatabase::class.java,
            "cache.db"
        ).build()

    @Provides
    fun providesFameDao(database: CacheDatabase): FameDao = database.fameDao()

    @Provides
    fun providesCharacterDao(database: CacheDatabase): CharacterDao = database.characterDao()
}
