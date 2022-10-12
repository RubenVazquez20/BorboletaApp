package com.example.borboletaapp4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.borboletaapp4.databinding.ActivityFilterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FilterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_filter)

        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth


        //Agregar direccionamiento hacia la pantalla de especialista inicial
        //binding.continueButtonFilter.setOnClickListener {
          //  val intent = Intent(this, Registr)
            //this.startActivity(intent)
        //}

    }



}