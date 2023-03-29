package com.example.foodrecipipaging.model


import com.google.gson.annotations.SerializedName

data class WeightPerServing(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("unit")
    val unit: String?
)