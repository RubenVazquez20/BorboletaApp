package com.example.borboletaapp4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.borboletaapp4.models.card

class CardStackAdapter(
    private var cards: List<card> = emptyList()): RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.card,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.name.text = "${card.id}. ${card.name}"
        holder.role.text = card.role
        holder.description.text = card.description
        holder.image.setImageDrawable(card.image)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun setCards(spots: List<card>) {
        this.cards = spots
    }

    fun getCards(): List<card> {
        println(cards)
        return cards
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val role: TextView = view.findViewById(R.id.role)
        val description: TextView = view.findViewById(R.id.description)
        val image: ImageView = view.findViewById(R.id.image)
    }

}