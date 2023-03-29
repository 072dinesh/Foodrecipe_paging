package com.example.foodrecipipaging.fragment.searchfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.foodrecipipaging.databinding.IngredientsBinding
import com.example.foodrecipipaging.model.ExtendedIngredient
import com.example.foodrecipipaging.util.DiffUtilExt

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    //private var listData : List<GridViewData>?=null
    private var callList = emptyList<ExtendedIngredient>()
    class MyViewHolder(private val binding : IngredientsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            currentItem: ExtendedIngredient,
//            onMainClick: (ExtendedIngredient) -> Unit
//            onAlbumClick: (resipi) -> Unit
        ) {
            binding.ingredientclick = currentItem
//            binding.root.setOnClickListener {
//                onMainClick(currentItem)
//           }


        }
        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientsBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }


    override fun getItemCount(): Int {
        return callList.size
    }



    fun setData(Result:List<ExtendedIngredient>){
//        this.callList= user
//        notifyDataSetChanged()

        val userDiffUtil = DiffUtilExt(callList, Result)
        val userDiffUtilResult = DiffUtil.calculateDiff(userDiffUtil)
        callList = Result
        userDiffUtilResult.dispatchUpdatesTo(this)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = callList.getOrNull(position)

        currentItem?.let {
            holder.bind(it)
        }
    }


}