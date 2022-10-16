package com.example.borboletaapp4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.borboletaapp4.databinding.ActivityConfigurationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
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

        val currentUser = auth.currentUser
        //val uid = currentUser!!.uid
        val db = Firebase.firestore

        db.collection("userData").document(auth.currentUser?.email.toString()).get().addOnSuccessListener {
            binding.nameUserData.text = ("Nombre: " + it.get("nombre") as String?)
            binding.lastNameUserData.text = ("ApellidoPaterno: " + it.get("apellidoPaterno") as String?)
            binding.lastNameMUserData.text = ("ApellidoMaterno: " + it.get("ApellidoMaterno") as String?)
            binding.DDUserData.text = ("DD_registration: " + it.get("DD_registration") as String?)
            binding.MMUserData.text = ("MM_registration: " + it.get("MM_registration") as String?)
            binding.AAAAUserData.text = ("AAAA_registration" + it.get("AAAA_registration") as String?)
            binding.genderUserData.text = ("gender: " + it.get("gender") as String?)
            binding.pronounUserData.text = ("pronoun: " + it.get("pronoun") as String?)
        }


        //Agregar direccionamiento hacia la pantalla de filtrado
        binding.filterButon.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            this.startActivity(intent)
        }


    }


    private fun cerrarSesion() {
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(
            intent
        )
    }


    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        } else {

        }
    }

    private fun reload() {

    }
}