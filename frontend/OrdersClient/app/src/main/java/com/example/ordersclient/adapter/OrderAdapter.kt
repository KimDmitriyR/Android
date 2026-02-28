package com.example.ordersclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ordersclient.R
import com.example.ordersclient.model.Order

class OrderAdapter(
    private var orders: List<Order>,
    private val onDeleteClick: (Order) -> Unit,
    private val onItemClick: (Order) -> Unit   
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTiker: TextView = itemView.findViewById(R.id.textTiker)
        val textCount: TextView = itemView.findViewById(R.id.textCount)
        val textDuration: TextView = itemView.findViewById(R.id.textDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]

        holder.textTiker.text = order.tiker
        holder.textCount.text = "Количество: ${order.count}"
        holder.textDuration.text = "Срок: ${order.duration}"

        holder.itemView.setOnLongClickListener {
            onDeleteClick(order)
            true
        }

        holder.itemView.setOnClickListener {
            onItemClick(order)
        }
    }

    override fun getItemCount(): Int = orders.size

    fun updateData(newOrders: List<Order>) {
        orders = newOrders
        notifyDataSetChanged()
    }
}
