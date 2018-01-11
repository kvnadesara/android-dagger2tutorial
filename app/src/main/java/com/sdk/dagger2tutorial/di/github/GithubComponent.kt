package com.sdk.dagger2tutorial.di.github

import com.sdk.dagger2tutorial.di.AppComponent
import com.sdk.dagger2tutorial.ui.github.GithubActivity
import dagger.Component

/**
 * @author kevin.adesara on 03/01/18.
 */

@GithubScope
@Component(dependencies = [AppComponent::class])
interface GithubComponent {
    fun inject(activity: GithubActivity)
}