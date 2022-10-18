package com.example.borboletaapp4.implementations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.borboletaapp4.R

class ReScheduleCardAdapter:RecyclerView.Adapter<ReScheduleCardAdapter.ViewHolder>() {
    private var exact = arrayOf("Viernes 07 ","Sabado 08","Domingo 09")
    private var month = arrayOf("Febrero 2022","Febrero 2022","Febrero 2022")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.schedule_card,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.exactDate.text=exact[position]
        holder.monthDate.text=month[position]
        holder.timeRecyView.adapter=TimeScheduleAdapter()
    }

    override fun getItemCount(): Int {
        return exact.size
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var exactDate: TextView
        var monthDate: TextView
        var timeRecyView: RecyclerView
        init {
            exactDate = itemView.findViewById(R.id.schCard_txt_ExactDate)
            monthDate = itemView.findViewById(R.id.schCard_txt_MonthDate)
            timeRecyView = itemView.findViewById(R.id.schCard_recyclerView_Horas)
            timeRecyView.layoutManager=StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        }
    }


}