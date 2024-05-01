package today.pathos.android.portfolio.core.di

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.util.DebugLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import today.pathos.android.portfolio.BuildConfig
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ViewModule {
    @Singleton
    @Provides
    fun providesImageLoader(
        client: OkHttpClient,
        @ApplicationContext context: Context,
    ): ImageLoader = ImageLoader.Builder(context)
        .callFactory { client }
        .crossfade(true)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.25)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .maxSizePercent(0.02)
                .build()
        }
        .apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger())
            }
        }
        .build()
}
