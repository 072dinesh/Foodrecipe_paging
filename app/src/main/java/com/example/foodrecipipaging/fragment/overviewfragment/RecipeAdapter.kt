package com.example.foodrecipipaging.fragment.overviewfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipipaging.model.Result
import com.example.foodrecipipaging.databinding.ItemFoodBinding
import com.example.foodrecipipaging.util.DiffUtilExt


class RecipeAdapter(val onMainClick: (Result) -> Unit) : PagingDataAdapter<com.example.foodrecipipaging.model.Result, RecipeAdapter.DataPagingViewHolder>(
    COMPARATOR) {
    private var callList = emptyList<com.example.foodrecipipaging.model.Result>()
    class DataPagingViewHolder(private val binding : ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem : com.example.foodrecipipaging.model.Result,
                 onMainClick: (Result) -> Unit

        ){
            binding.foodclick = currentItem
            binding.root.setOnClickListener {
                onMainClick(currentItem)
            }
        }
        companion object{
            fun from(parent: ViewGroup): DataPagingViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFoodBinding.inflate(layoutInflater, parent, false)
                return DataPagingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: DataPagingViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it, onMainClick)
            //Log.e("dd",it.date.toString())
            //Timber.e("id",it.id)
        }
    }

//    fun setData(Result: List<com.example.food_app.model.Result>) {
////        this.callList= user
////        notifyDataSetChanged()
//
//        val userDiffUtil = DiffUtilExt(callList, Result)
//        val userDiffUtilResult = DiffUtil.calculateDiff(userDiffUtil)
//        callList = Result
//        userDiffUtilResult.dispatchUpdatesTo(this)
//
//    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataPagingViewHolder {
        return DataPagingViewHolder.from(parent)
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<com.example.foodrecipipaging.model.Result>(){
            override fun areItemsTheSame(oldItem: com.example.foodrecipipaging.model.Result, newItem: com.example.foodrecipipaging.model.Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: com.example.foodrecipipaging.model.Result, newItem: com.example.foodrecipipaging.model.Result): Boolean {
                return oldItem == newItem
            }

        }
    }


}