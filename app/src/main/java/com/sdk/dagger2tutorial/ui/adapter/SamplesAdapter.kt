package com.sdk.dagger2tutorial.ui.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sdk.dagger2tutorial.R
import kotlinx.android.synthetic.main.raw_sample_item.view.*

/**
 * @author kevin.adesara on 03/01/18.
 */

class SamplesAdapter(private val samples: Array<String>,
                     private val itemClickListener: OnRecyclerViewItemClickListener<String>)
    : RecyclerView.Adapter<SamplesAdapter.SampleViewHolder>() {

    override fun onBindViewHolder(holder: SampleViewHolder?, position: Int) {
        holder?.bind(samples[position], itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SampleViewHolder {
        val v = parent?.inflate(R.layout.raw_sample_item)!!
        return SampleViewHolder(v)
    }

    override fun getItemCount() = samples.size

    class SampleViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var view: View = itemView
        private var sample: String = ""
        private lateinit var itemClickListener: OnRecyclerViewItemClickListener<String>

        init {
            view.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(sample: String, itemClickListener: OnRecyclerViewItemClickListener<String>) {
            this.sample = sample
            this.itemClickListener = itemClickListener
            view.lblSampleName.text = sample
        }

        override fun onClick(v: View?) {
            itemClickListener.onItemClick(this.sample)
        }
    }
}