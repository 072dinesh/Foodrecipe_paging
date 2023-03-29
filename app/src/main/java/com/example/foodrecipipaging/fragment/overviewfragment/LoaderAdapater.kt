package com.example.foodrecipipaging.fragment.overviewfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipipaging.R

import kotlinx.android.synthetic.main.loder_item.view.*

class LoaderAdapater : LoadStateAdapter<LoaderAdapater.LoaderViewHolder> (){

    class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val progressBar = itemView.progress_br

        fun bind(loadState: LoadState){
            progressBar.isVisible = loadState is LoadState.Loading
        }

    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {

        holder.bind(loadState)

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loder_item,parent,false)
    return LoaderViewHolder(view)
    }

}