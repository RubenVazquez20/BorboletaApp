package com.example.borboletaapp4.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.borboletaapp4.R
import com.example.borboletaapp4.databinding.ActivityConfigurationBinding
import com.example.borboletaapp4.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    private var list = mutableListOf<CarouselItem>()
    //Atributos para llamar a firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        //Métodos

        val currentUser = auth.currentUser
        //val uid = currentUser!!.uid
        val db = Firebase.firestore

        db.collection("userData").document(auth.currentUser?.email.toString()).get().addOnSuccessListener {
            binding.tvBienvenida.text = "Hola " + (it.get("nombre") as String?)
        }

        // Variables Saludo
        val saludo = findViewById<TextView>(R.id.tv_bienvenida)
        val pronombre = "él"
        val nombre = "Óscar"
        when(pronombre){
            "él" -> saludo.text = "Bienvenido $nombre"
            "ella" -> saludo.text = "Bienvenida $nombre"
            "elle" -> saludo.text = "Bienvenide $nombre"
        }

        // Tarjeta de acompañamiento emocional y mentoría
        val emocional = findViewById<CardView>(R.id.cv_psicContainer)
        val mentoria = findViewById<CardView>(R.id.cv_mentorContainer)

        // Artículos de carousel
        val carousel: ImageCarousel = findViewById(R.id.carousel_revista)
        list.add(CarouselItem("https://assets.zyrosite.com/cdn-cgi/image/format=auto,w=600,h=360,fit=crop/AR00Q0oo5WcJbPGn/artaculo-mjE0bQ3eNOIOZQBL.jpg", "La simplicidad de un círculo"))
        list.add(CarouselItem("https://assets.zyrosite.com/cdn-cgi/image/format=auto,w=861,h=517,fit=crop/AR00Q0oo5WcJbPGn/2-AVLG1ZgRvluyPyZb.png", "Depresión y ansiedad en tiempos de post pandemia"))
        list.add(CarouselItem("https://assets.zyrosite.com/cdn-cgi/image/format=auto,w=861,h=517,fit=crop/AR00Q0oo5WcJbPGn/3-mp8zqgg59BHGr8Pr.png", "Lobos y emprendedores"))
        list.add(CarouselItem("https://assets.zyrosite.com/cdn-cgi/image/format=auto,w=861,h=517,fit=crop/AR00Q0oo5WcJbPGn/portadas-de-articulos-2-m2W55nZyZNh1eGZV.jpg", "Emprendedores sociales, ¿los nuevos súper héroes?"))
        list.add(CarouselItem("https://assets.zyrosite.com/cdn-cgi/image/format=auto,w=861,h=517,fit=crop/AR00Q0oo5WcJbPGn/portadas-de-articulos-AoPykRk91ZCo4wbD.jpg", "LA ANSIEDAD EN LOS UNIVERSITARIOS: ¿Cómo detectarla?\n"))

        // Carousel listener
        carousel.carouselListener = object : CarouselListener {
            override fun onClick(position: Int, carouselItem: CarouselItem) {
                revista()
            }
        }
        carousel.addData(list)
    }
    private fun revista(){
        val intent = Intent(this,RevistaActivity::class.java)
        startActivity(intent)
    }
}