package com.example.borboletaapp4

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    val daysRegistration = arrayOf("DD","01","02","03","04","05","06","07","08","09","10","11","12","13",
                                   "14","15","16","17","18","19","20","21","22","23","24","25","26",
                                   "27","28","29","30","31")

    val monthsRegistration = arrayOf("MM","Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
        "Septiembre","Octubre","Noviembre","Diciembre")

    val yearsRegistration = arrayOf("AAAA","1990","1991","1992","1993","1994","1995","1996","1997","1998",
        "1999","2000")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val arrayAdapter = ArrayAdapter<String>(this,R.layout.style_spinner,daysRegistration)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Toast.makeText(applicationContext, "selected day is = "+daysRegistration[p2], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        val arrayAdapter2 = ArrayAdapter<String>(this,R.layout.style_spinner,monthsRegistration)
        spinner2.adapter = arrayAdapter2
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Toast.makeText(applicationContext, "selected day is = "+monthsRegistration[p2], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val spinner3 = findViewById<Spinner>(R.id.spinner3)
        val arrayAdapter3 = ArrayAdapter<String>(this,R.layout.style_spinner,yearsRegistration)
        spinner3.adapter = arrayAdapter3
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Toast.makeText(applicationContext, "selected day is = "+yearsRegistration[p2], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

    }
}