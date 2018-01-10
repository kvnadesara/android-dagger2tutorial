package com.sdk.dagger2tutorial.di

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * @author kevin.adesara on 03/01/18.
 */

@Module
class ContextModule(val context: Context) {

    @Provides
    @AppScope
    @ApplicationContext
    fun context(): Context = context.applicationContext
}