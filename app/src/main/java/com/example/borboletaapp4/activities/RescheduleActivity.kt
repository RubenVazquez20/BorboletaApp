package com.example.borboletaapp4.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.borboletaapp4.R
import com.example.borboletaapp4.implementations.AlarmCardAdapter
import com.example.borboletaapp4.implementations.ReScheduleCardAdapter

class RescheduleActivity:AppCompatActivity() {

    private var scheduleLayoutManager: RecyclerView.LayoutManager? = null
    private var alarmLayoutManager: RecyclerView.LayoutManager? = null
    private var scheduleAdapter: RecyclerView.Adapter<ReScheduleCardAdapter.ViewHolder>?=null
    private var alarmAdapter: RecyclerView.Adapter<AlarmCardAdapter.ViewHolder>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reschedule)

        scheduleLayoutManager=StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        alarmLayoutManager=StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        val scheduleRecyclerView:RecyclerView = findViewById<RecyclerView>(R.id.Reschedule_recyclerView_Schedule)
        val alarmRecyclerView:RecyclerView= findViewById<RecyclerView>(R.id.ReSchedule_recyclerView_Alarm)

        scheduleRecyclerView.layoutManager=scheduleLayoutManager
        alarmRecyclerView.layoutManager=alarmLayoutManager

        scheduleAdapter = ReScheduleCardAdapter()
        alarmAdapter= AlarmCardAdapter()
        scheduleRecyclerView.adapter = scheduleAdapter
        alarmRecyclerView.adapter=alarmAdapter
    }
}