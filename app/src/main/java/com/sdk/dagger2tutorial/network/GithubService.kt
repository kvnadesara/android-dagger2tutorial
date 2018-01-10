package com.sdk.dagger2tutorial.network

import com.sdk.dagger2tutorial.model.Repo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author kevin.adesara on 02/01/18.
 */

interface GithubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>
}
