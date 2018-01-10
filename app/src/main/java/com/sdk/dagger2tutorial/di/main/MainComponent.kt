package com.sdk.dagger2tutorial.di.main

import com.sdk.dagger2tutorial.di.AppComponent
import com.sdk.dagger2tutorial.ui.MainActivity
import dagger.Component

/**
 * @author kevin.adesara on 03/01/18.
 */

@MainScope
@Component(dependencies = arrayOf(AppComponent::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}