package com.sdk.dagger2tutorial.ui.adapter

/**
 * @author kevin.adesara on 11/01/18.
 */
interface OnRecyclerViewItemClickListener<in T> {
    fun onItemClick(item: T)
}