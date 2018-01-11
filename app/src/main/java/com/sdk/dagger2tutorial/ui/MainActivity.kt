package com.sdk.dagger2tutorial.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sdk.dagger2tutorial.R
import com.sdk.dagger2tutorial.ui.adapter.OnRecyclerViewItemClickListener
import com.sdk.dagger2tutorial.ui.adapter.SamplesAdapter
import com.sdk.dagger2tutorial.ui.github.GithubActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val samplesMap = linkedMapOf("GitHub" to GithubActivity::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTutorials.layoutManager = LinearLayoutManager(this)
        val samples = samplesMap.keys.toTypedArray()
        rvTutorials.adapter = SamplesAdapter(samples,
                object : OnRecyclerViewItemClickListener<String> {
                    override fun onItemClick(item: String) {
                        val intent = Intent(this@MainActivity, samplesMap[item])
                        startActivity(intent)
                    }
                })
    }
}
