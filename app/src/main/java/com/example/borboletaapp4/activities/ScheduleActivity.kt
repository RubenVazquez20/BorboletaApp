package com.example.borboletaapp4.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.borboletaapp4.R
import com.example.borboletaapp4.implementations.AlarmCardAdapter
import com.example.borboletaapp4.implementations.ReScheduleCardAdapter
import com.example.borboletaapp4.models.schedule
import com.google.firebase.firestore.FirebaseFirestore

class ScheduleActivity:AppCompatActivity() {

    private var scheduleLayoutManager: RecyclerView.LayoutManager? = null
    private var alarmLayoutManager: RecyclerView.LayoutManager? = null
    private var scheduleAdapter: RecyclerView.Adapter<ReScheduleCardAdapter.ViewHolder>?=null
    private var alarmAdapter: RecyclerView.Adapter<AlarmCardAdapter.ViewHolder>?=null
    private var arrSch = arrayListOf<schedule>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        scheduleLayoutManager= StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        alarmLayoutManager= StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        val scheduleRecyclerView: RecyclerView = findViewById<RecyclerView>(R.id.Reschedule_recyclerView_Schedule)
        val alarmRecyclerView: RecyclerView = findViewById<RecyclerView>(R.id.ReSchedule_recyclerView_Alarm)

        scheduleRecyclerView.layoutManager=scheduleLayoutManager
        alarmRecyclerView.layoutManager=alarmLayoutManager

        scheduleAdapter = ReScheduleCardAdapter()
        alarmAdapter= AlarmCardAdapter()
        scheduleRecyclerView.adapter = scheduleAdapter
        alarmRecyclerView.adapter=alarmAdapter
    }

    //private fun createSchCards(): List<String>{

    //}

    private fun readData(){
        val db = FirebaseFirestore.getInstance()
        db.collection("profesionales")
            .get().addOnCompleteListener{
                if(it.isSuccessful){
                    for(document in it.result!!){
                        arrSch.add(
                            schedule(
                                document.data.getValue("exact") as Array<String>,
                                document.data.getValue("month") as Array<String>,
                                document.data.getValue("time") as Array<String>
                            )
                        )
                    }
                }
            }
    }
}