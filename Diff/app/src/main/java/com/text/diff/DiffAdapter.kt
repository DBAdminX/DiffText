package com.text.diff

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


class DiffAdapter : RecyclerView.Adapter<DiffAdapter.ViewHolder>() {
    private val items = mutableListOf<DiffItem>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvLine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_diff_line, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item.text
        holder.textView.setBackgroundColor(
            when (item.type) {
                DiffType.ADDED -> Color.parseColor("#E8F5E9") // Green
                DiffType.REMOVED -> Color.parseColor("#FFEBEE") // Red
                else -> Color.TRANSPARENT
            }
        )
    }

    override fun getItemCount() = items.size

    fun updateList(newList: List<DiffItem>) {
        val diffCallback = DiffCallback(items, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}