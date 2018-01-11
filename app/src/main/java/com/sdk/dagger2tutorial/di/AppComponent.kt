package com.sdk.dagger2tutorial.di

import com.sdk.dagger2tutorial.network.GithubService
import com.squareup.picasso.Picasso
import dagger.Component

/**
 * @author kevin.adesara on 03/01/18.
 */

@AppScope
@Component(modules = [GithubServiceModule::class, PicassoModule::class])
interface AppComponent {
    fun getGithubService(): GithubService

    fun getPicasso(): Picasso
}