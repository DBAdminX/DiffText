package com.text.diff

import androidx.recyclerview.widget.DiffUtil

class DiffCallback(
    private val oldList: List<DiffItem>,
    private val newList: List<DiffItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return oldList[oldPos].text == newList[newPos].text
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return oldList[oldPos] == newList[newPos]
    }
}