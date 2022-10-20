package com.example.borboletaapp4.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.borboletaapp4.R
import com.example.borboletaapp4.databinding.ActivityConfigurationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
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
            binding.nameUserData.text = (it.get("nombre") as String?)
            binding.lastNameUserData.text = (it.get("apellidoPaterno") as String?)
            binding.lastNameMUserData.text = (it.get("apellidoMaterno") as String?)
            binding.DDUserData.text = (it.get("DD_registration") as String?)
            binding.MMUserData.text = (it.get("MM_registration") as String?)
            binding.AAAAUserData.text = (it.get("AAAA_registration") as String?)
            binding.genderUserData.text = (it.get("gende") as String?)
            binding.pronounUserData.text = (it.get("pronou") as String?)
        }


        /*Agregar direccionamiento hacia la pantalla de filtrado
        binding.filterButon.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            this.startActivity(intent)
        }*/


        //Agregar direccionamiento hacia la pantalla de filtrado
        binding.signOutButon.setOnClickListener {
            cerrarSesion()
        }

        userImage()

    }


    private fun cerrarSesion() {
        Firebase.auth.signOut()
        val intent = Intent(this, SignInActivity::class.java)
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

    //Funcion de imageUser
    fun userImage () {
        val user = auth.currentUser


        if (user != null) {
            Glide
                .with(this)
                .load(user.photoUrl)
                .centerCrop()
                .placeholder(R.drawable.first)
                .into(binding.imageView2)
        }

    }
}
