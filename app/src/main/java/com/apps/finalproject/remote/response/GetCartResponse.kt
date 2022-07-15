package com.apps.finalproject.remote.response

import com.google.gson.annotations.SerializedName

data class GetCartResponse(
    @field:SerializedName("success")
    val success: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("data")
    val data: getCart
    )

data class getCart(
    @field:SerializedName("overview")
    val overview: CartOverview,
    @field:SerializedName("cartItems")
    val cartItems: List<CartItems>
)

data class CartOverview(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("promo")
    val promo: String,
    @field:SerializedName("delivery")
    val delivery: String,
    @field:SerializedName("total")
    val total: Int,
    @field:SerializedName("weight")
    val weight: Int,
    @field:SerializedName("costDelivery")
    val costDelivery: Int
)

data class CartItems(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("brandName")
    val brandName: String,
    @field:SerializedName("banner")
    val banner: String,
    @field:SerializedName("items")
    val items: List<ItemVarian>
    )

data class ItemVarian(
    @field:SerializedName("variantId")
    val variantId: String,
    @field:SerializedName("productId")
    val productId: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("image")
    val image: String,
    @field:SerializedName("price")
    val price: Int,
    @field:SerializedName("quantity")
    val quantity: Int,
    @field:SerializedName("subTotal")
    val subTotal: Int,
    @field:SerializedName("isChecked")
    val isChecked: Boolean
)
