package com.example.ordersclient.model

data class Order(
    val id: Long? = null,
    val tiker: String = "",
    val count: Int = 0,
    val type: String = "",
    val number: Int = 0,
    val data: String = "",
    val duration: String = ""
)