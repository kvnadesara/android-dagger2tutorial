package com.sdk.dagger2tutorial.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sdk.dagger2tutorial.App
import com.sdk.dagger2tutorial.R
import com.sdk.dagger2tutorial.di.main.DaggerMainComponent
import com.sdk.dagger2tutorial.model.Repo
import com.sdk.dagger2tutorial.network.GithubService
import com.sdk.dagger2tutorial.ui.adapter.RepoAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var listRepoCall: Call<List<Repo>>? = null

    @Inject
    lateinit var repoAdapter: RepoAdapter

    @Inject
    lateinit var githubService: GithubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = DaggerMainComponent.builder()
                .appComponent(App.get(this).component)
                .build()
        component.inject(this)

        rvRepo.layoutManager = LinearLayoutManager(this)
        rvRepo.adapter = repoAdapter

        callListRepos()
    }

    private fun callListRepos() {
        listRepoCall = githubService.listRepos("google")
        listRepoCall?.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                response?.let {
                    Timber.i(it.body().toString())
                    repoAdapter.swapData(it.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                Timber.e(t)
            }

        })
    }

    override fun onStop() {
        listRepoCall?.cancel()
        super.onStop()
    }
}
