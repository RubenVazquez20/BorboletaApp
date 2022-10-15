package com.example.borboletaapp4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.borboletaapp4.databinding.ActivityConfigurationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ConfigurationActivity : AppCompatActivity() {

    //Atributos para llamar a firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        //Métodos


        //Agregar direccionamiento hacia la pantalla de filtrado
        binding.filterButon.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            this.startActivity(intent)
        }


    }
}