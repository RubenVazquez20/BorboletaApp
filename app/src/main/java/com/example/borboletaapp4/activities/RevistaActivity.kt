package com.example.borboletaapp4.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.TextView
import com.example.borboletaapp4.R

class RevistaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revista)

        //Botón backToMain
        val btnBackToMain = findViewById<ImageButton>(R.id.imgb_close)
        btnBackToMain.setOnClickListener{
            backToHome()
        }

        // Variables Web View
        val titulo = "Depresión y ansiedad en tiempos de post pandemia"
        val link = "https://www.borboletamx.com/depresiony-ansiedadentiemposdepostpandemia"

        // Configuración de campos de texto
        val encabezado = findViewById<TextView>(R.id.txtv_encabezado)
        encabezado.text = titulo
        val enlace = findViewById<TextView>(R.id.txtv_enlace)
        enlace.text = link

        // Web View básico
        val visor = findViewById<WebView>(R.id.webv_revista)
        visor.webChromeClient = object : WebChromeClient(){}
        visor.webViewClient = object : WebViewClient(){}
        val settings: WebSettings = visor.settings
        settings.javaScriptEnabled = true
        visor.loadUrl(link)
    }
    override fun onBackPressed(){
        val visor = findViewById<WebView>(R.id.webv_revista)
        if(visor.canGoBack()){
            visor.goBack()
        } else{
            super.onBackPressed()
        }
    }
    //Moverse entre actividades
    private fun backToHome(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}