package com.apps.finalproject.remote.response

import com.apps.finalproject.remote.model.User
import com.google.gson.annotations.SerializedName

data class OrdersResponse(
    @field:SerializedName("success")
    val success: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("data")
    val data: List<DataOrders>
    )

data class DataOrders(
    @field:SerializedName("id")
    val id: String,
//    @field:SerializedName("user")
//    val user: User?,
//    @field:SerializedName("address")
//    val address: String?,
//    @field:SerializedName("orders")
//    val orders: List<ItemOrders>,
//    @field:SerializedName("promo")
//    val promo: String?,
//    @field:SerializedName("delivery")
//    val delivery: String,
//    @field:SerializedName("status")
//    val status: String,
//    @field:SerializedName("date")
//    val date: Long,
//    @field:SerializedName("total")
//    val total: Int,
)

data class ItemOrders(
    @field:SerializedName("id")
    val id: IdOrders,
    @field:SerializedName("productPrice")
    val productPrice: String,
    @field:SerializedName("quantity")
    val quantity: String,
    @field:SerializedName("subTotal")
    val subTotal: Int
)

data class IdOrders(
    @field:SerializedName("orderId")
    val orderId: String,
    @field:SerializedName("variantId")
    val variantId: String
    )
