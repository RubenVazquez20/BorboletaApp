package com.example.borboletaapp4.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.borboletaapp4.R
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    private var list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Variables Saludo
        val saludo = findViewById<TextView>(R.id.tv_bienvenida)
        val pronombre = "él"
        val nombre = "Óscar"
        when(pronombre){
            "él" -> saludo.text = "Bienvenido $nombre"
            "ella" -> saludo.text = "Bienvenida $nombre"
            "elle" -> saludo.text = "Bienvenide $nombre"
        }

        // Artículos de carousel
        val carousel: ImageCarousel = findViewById(R.id.carousel_revista)
        list.add(CarouselItem("https://images.pexels.com/photos/4101143/pexels-photo-4101143.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Publicación 1"))
        list.add(CarouselItem("https://images.pexels.com/photos/4101188/pexels-photo-4101188.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Publicación 2"))
        list.add(CarouselItem("https://images.pexels.com/photos/4051134/pexels-photo-4051134.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Publicación 3"))
        list.add(CarouselItem("https://images.pexels.com/photos/235922/pexels-photo-235922.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Publicación 4"))

        // Carousel listener
        carousel.carouselListener = object : CarouselListener {
            override fun onClick(position: Int, carouselItem: CarouselItem) {
                Toast.makeText(this@HomeActivity,"Foto ${carouselItem.caption}", Toast.LENGTH_SHORT).show()
                //revista()
            }
        }
        carousel.addData(list)
    }
}