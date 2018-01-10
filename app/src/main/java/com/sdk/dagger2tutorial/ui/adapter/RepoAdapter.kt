package com.sdk.dagger2tutorial.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sdk.dagger2tutorial.R
import com.sdk.dagger2tutorial.model.Repo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.raw_repo_item.view.*
import javax.inject.Inject

/**
 * @author kevin.adesara on 03/01/18.
 */

class RepoAdapter @Inject constructor(val picasso: Picasso) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    private var repos: List<Repo> = ArrayList()

    fun swapData(repos: List<Repo>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RepoViewHolder?, position: Int) {
        holder?.bind(repos[position], picasso)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepoViewHolder {
        val v = parent?.inflate(R.layout.raw_repo_item)
        return RepoViewHolder(v!!)
    }

    override fun getItemCount() = repos.size

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var view: View = itemView
        private var repo: Repo? = null

        fun bind(repo: Repo, picasso: Picasso) {
            this.repo = repo
            view.lblRepoName.text = this.repo?.name
            view.lblCreatedAt.text = this.repo?.createdAt.toString()
            view.lblUpdatedAt.text = this.repo?.updatedAt.toString()
            view.lblHtmlUrl.text = this.repo?.htmlUrl
            picasso.load(this.repo?.owner?.avatarUrl).into(view.imgAvatar)
        }
    }
}

// Extend ViewGroup class with inflate function
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}