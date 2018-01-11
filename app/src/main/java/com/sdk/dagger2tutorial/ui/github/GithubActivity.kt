package com.sdk.dagger2tutorial.ui.github

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sdk.dagger2tutorial.App
import com.sdk.dagger2tutorial.R
import com.sdk.dagger2tutorial.di.github.DaggerGithubComponent
import com.sdk.dagger2tutorial.model.Repo
import com.sdk.dagger2tutorial.network.GithubService
import com.sdk.dagger2tutorial.ui.adapter.RepoAdapter
import kotlinx.android.synthetic.main.activity_github.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.inject.Inject

class GithubActivity : AppCompatActivity() {

    private var listRepoCall: Call<List<Repo>>? = null

    @Inject
    lateinit var repoAdapter: RepoAdapter

    @Inject
    lateinit var githubService: GithubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)

        val component = DaggerGithubComponent.builder()
                .appComponent(App.get(this).component)
                .build()
        component.inject(this)

        rvRepo.layoutManager = LinearLayoutManager(this)
        rvRepo.adapter = repoAdapter

        callListRepos()
    }

    private fun callListRepos() {
        listRepoCall = githubService.listRepos("google")
        progressBar.visibility = View.VISIBLE
        listRepoCall?.enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                progressBar.visibility = View.GONE
                response?.let {
                    Timber.i(it.body().toString())
                    repoAdapter.swapData(it.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                Timber.e(t)
                progressBar.visibility = View.GONE
                var message = "Unknown error occurred"
                when (t) {
                    is SocketTimeoutException -> message = "Please check your internet connection"
                }
                Snackbar.make(rvRepo, message, Snackbar.LENGTH_SHORT).show()
            }

        })
    }

    override fun onStop() {
        listRepoCall?.cancel()
        super.onStop()
    }
}
