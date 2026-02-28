package com.example.ordersclient

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ordersclient.adapter.OrderAdapter
import com.example.ordersclient.api.RetrofitClient
import com.example.ordersclient.model.Order
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderAdapter

    override fun onResume() {
        super.onResume()
        loadOrders()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewOrders)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = OrderAdapter(
            emptyList(),

            { order ->
                deleteOrder(order.id!!)
            },

            { order ->
                val intent = Intent(this, AddOrderActivity::class.java)
                intent.putExtra("orderId", order.id)
                intent.putExtra("tiker", order.tiker)
                intent.putExtra("count", order.count)
                intent.putExtra("type", order.type)
                intent.putExtra("number", order.number)
                intent.putExtra("data", order.data)
                intent.putExtra("duration", order.duration)
                startActivity(intent)
            }
        )
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            startActivity(Intent(this, AddOrderActivity::class.java))
        }

        findViewById<Button>(R.id.buttonRefresh).setOnClickListener {
            loadOrders()
        }

        loadOrders()
    }

    private fun loadOrders() {
        RetrofitClient.api.getOrders().enqueue(object : Callback<List<Order>> {
            override fun onResponse(
                call: Call<List<Order>>,
                response: Response<List<Order>>
            ) {
                response.body()?.let {
                    adapter.updateData(it)
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun createTestOrder() {

        val newOrder = Order(
            id = null,
            tiker = "AAPL",
            count = (1..100).random(),
            type = "Покупка",
            number = (1000..9999).random(),
            data = "2024-06-01",
            duration = "1 день"
        )

        RetrofitClient.api.createOrder(newOrder)
            .enqueue(object : Callback<Order> {
                override fun onResponse(call: Call<Order>, response: Response<Order>) {
                    loadOrders()
                }

                override fun onFailure(call: Call<Order>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun deleteOrder(id: Long) {
        RetrofitClient.api.deleteOrder(id)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    loadOrders()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun openEditScreen(order: Order) {
        val intent = Intent(this, AddOrderActivity::class.java)
        intent.putExtra("orderId", order.id)
        startActivity(intent)
    }
}
