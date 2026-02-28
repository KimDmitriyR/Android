package com.example.ordersclient

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ordersclient.api.RetrofitClient
import com.example.ordersclient.model.Order
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddOrderActivity : AppCompatActivity() {

    private var orderId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

        val editTiker = findViewById<EditText>(R.id.editTiker)
        val editCount = findViewById<EditText>(R.id.editCount)
        val editType = findViewById<EditText>(R.id.editType)
        val editDuration = findViewById<EditText>(R.id.editDuration)

        orderId = intent.getLongExtra("orderId", -1L)

        if (orderId != -1L) {
            editTiker.setText(intent.getStringExtra("tiker"))
            editCount.setText(intent.getIntExtra("count", 0).toString())
            editType.setText(intent.getStringExtra("type"))
            editDuration.setText(intent.getStringExtra("duration"))
        }

        findViewById<Button>(R.id.buttonSave).setOnClickListener {

            val tiker = editTiker.text.toString()
            val count = editCount.text.toString().toIntOrNull() ?: 0
            val type = editType.text.toString()
            val duration = editDuration.text.toString()

            if (tiker.isEmpty() || type.isEmpty()) {
                Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val order = Order(
                id = if (orderId == -1L) null else orderId,
                tiker = tiker,
                count = count,
                type = type,
                number = (1000..9999).random(),
                data = "2026-28-02",
                duration = duration
            )

            if (orderId == -1L) {

                RetrofitClient.api.createOrder(order)
                    .enqueue(object : Callback<Order> {
                        override fun onResponse(call: Call<Order>, response: Response<Order>) {
                            if (response.isSuccessful) {
                                Log.d("API", "CREATED: ${response.body()}")
                                Toast.makeText(this@AddOrderActivity, "Создано", Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Log.e("API", "ERROR CREATE: ${response.code()}")
                                Toast.makeText(this@AddOrderActivity, "Ошибка ${response.code()}", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Order>, t: Throwable) {
                            Log.e("API", "FAIL: ${t.message}")
                            Toast.makeText(this@AddOrderActivity, "Ошибка сети", Toast.LENGTH_SHORT).show()
                        }
                    })
            } else {

                RetrofitClient.api.updateOrder(orderId, order)
                    .enqueue(object : Callback<Order> {
                        override fun onResponse(call: Call<Order>, response: Response<Order>) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@AddOrderActivity, "Обновлено", Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Toast.makeText(this@AddOrderActivity, "Ошибка ${response.code()}", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Order>, t: Throwable) {
                            Toast.makeText(this@AddOrderActivity, "Ошибка сети", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
    }
}
