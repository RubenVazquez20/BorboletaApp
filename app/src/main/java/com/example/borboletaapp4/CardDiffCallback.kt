package com.example.borboletaapp4

import androidx.recyclerview.widget.DiffUtil
import com.example.borboletaapp4.models.card

class CardDiffCallback(
    private val old: List<card>,
    private val new: List<card>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].id == new[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }
}