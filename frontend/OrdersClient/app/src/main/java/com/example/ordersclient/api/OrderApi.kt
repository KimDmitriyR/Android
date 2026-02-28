package com.example.ordersclient.api

import com.example.ordersclient.model.Order
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Body

interface OrderApi {

    @GET("orders")
    fun getOrders(): Call<List<Order>>

    @POST("orders")
    fun createOrder(@Body order: Order): Call<Order>

    @PUT("orders/{id}")
    fun updateOrder(@Path("id") id: Long, @Body order: Order): Call<Order>

    @DELETE("orders/{id}")
    fun deleteOrder(@Path("id") id: Long): Call<Void>
}