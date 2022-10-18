package com.example.borboletaapp4.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.borboletaapp4.R
import com.example.borboletaapp4.databinding.ActivityFilterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FilterActivity : AppCompatActivity() {
    //-----npi
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        //-----npi
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        //binding.continueButtonFilter.setOnClickListener {
          //  val intent = Intent(this, Registr)
            //this.startActivity(intent)
        //}

        val button: Button = findViewById(R.id.continueButtonFilter)
        button.setOnClickListener{
            val intent = Intent(this@FilterActivity, GalleryActivity::class.java)
            var selectedFilters = sendSelected()
            intent.putExtra("selectedFilters", selectedFilters)
            startActivity(intent)
        }
    }

    private fun sendSelected(): ArrayList<String> {
        var selected = arrayListOf<String>()
        val t1: ToggleButton = findViewById(R.id.btn1)
        val t2: ToggleButton = findViewById(R.id.btn2)
        val t3: ToggleButton = findViewById(R.id.btn3)
        val t4: ToggleButton = findViewById(R.id.btn4)
        val t5: ToggleButton = findViewById(R.id.btn5)
        val t6: ToggleButton = findViewById(R.id.btn6)
        val t7: ToggleButton = findViewById(R.id.btn7)
        val t8: ToggleButton = findViewById(R.id.btn8)
        val t9: ToggleButton = findViewById(R.id.btn9)
        val t10: ToggleButton = findViewById(R.id.btn10)
        if(t1.isChecked) selected.add(t1.text as String)
        if(t2.isChecked) selected.add(t2.text as String)
        if(t3.isChecked) selected.add(t3.text as String)
        if(t4.isChecked) selected.add(t4.text as String)
        if(t5.isChecked) selected.add(t5.text as String)
        if(t6.isChecked) selected.add(t6.text as String)
        if(t7.isChecked) selected.add(t7.text as String)
        if(t8.isChecked) selected.add(t8.text as String)
        if(t9.isChecked) selected.add(t9.text as String)
        if(t10.isChecked) selected.add(t10.text as String)
        return selected
    }



}