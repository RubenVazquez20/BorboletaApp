package com.example.borboletaapp4.implementations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.borboletaapp4.R

class TimeScheduleAdapter: RecyclerView.Adapter<TimeScheduleAdapter.ViewHolder>() {
    private var time = arrayOf("10:30","12:00","14:20")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeScheduleAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.timestamp_adapter,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.timeSchedule.text=time[position]
    }

    override fun getItemCount(): Int {
        return time.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var timeSchedule: TextView
        init {
            timeSchedule = itemView.findViewById(R.id.tstmp_txt_Timestamp)
        }
    }
}