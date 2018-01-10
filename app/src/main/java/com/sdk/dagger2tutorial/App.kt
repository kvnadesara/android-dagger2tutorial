package com.sdk.dagger2tutorial

import android.app.Activity
import android.app.Application
import com.sdk.dagger2tutorial.di.AppComponent
import com.sdk.dagger2tutorial.di.ContextModule
import com.sdk.dagger2tutorial.di.DaggerAppComponent
import timber.log.Timber

/**
 * @author kevin.adesara on 02/01/18.
 */

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        component = DaggerAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()
    }

    companion object {
        fun get(activity: Activity): App {
            return activity.applicationContext as App
        }
    }
}