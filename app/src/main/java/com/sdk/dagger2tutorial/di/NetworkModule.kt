package com.sdk.dagger2tutorial.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

/**
 * @author kevin.adesara on 03/01/18.
 */

@Module(includes = arrayOf(ContextModule::class))
class NetworkModule {

    @Provides
    @AppScope
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.i(message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return loggingInterceptor
    }

    @Provides
    @AppScope
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) // 10 MB
    }

    @Provides
    @AppScope
    fun cacheFile(@ApplicationContext context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @AppScope
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build()
    }
}