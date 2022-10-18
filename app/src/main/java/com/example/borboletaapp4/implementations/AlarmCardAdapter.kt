package com.example.borboletaapp4.implementations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.borboletaapp4.R

class AlarmCardAdapter: RecyclerView.Adapter<AlarmCardAdapter.ViewHolder>()  {
    private var timestamp = arrayOf("13:00 ","14:00","15:00")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.alarm_card,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.timestamp.text=timestamp[position]
    }

    override fun getItemCount(): Int {
        return timestamp.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var timestamp: TextView
        init {
            timestamp = itemView.findViewById(R.id.alarm_txt_TimeStamp)
        }
    }


}