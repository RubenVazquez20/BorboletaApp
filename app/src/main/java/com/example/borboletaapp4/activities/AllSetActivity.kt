package com.example.borboletaapp4.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.borboletaapp4.databinding.ActivityAllSetBinding
import com.example.borboletaapp4.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AllSetActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAllSetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllSetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth



    }
}