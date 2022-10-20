package com.example.borboletaapp4.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.borboletaapp4.R
import com.example.borboletaapp4.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistrationBinding
    lateinit var datePickerDialog: DatePickerDialog
    private val fileResult = 1

    val daysRegistration = arrayOf("DD","01","02","03","04","05","06","07","08","09","10","11","12","13",
        "14","15","16","17","18","19","20","21","22","23","24","25","26",
        "27","28","29","30","31")

    val monthsRegistration = arrayOf("MM","Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
        "Septiembre","Octubre","Noviembre","Diciembre")

    val yearsRegistration = arrayOf("AAAA","1990","1991","1992","1993","1994","1995","1996","1997","1998",
        "1999","2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009")

    val genderRegistration = arrayOf("Género","Hombre", "Mujer", "Otro")

    val pronounRegistration = arrayOf("Pronombre","El", "Ella", "Elle")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

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

        val gender= findViewById<Spinner>(R.id.gender)
        val arrayAdapter4 = ArrayAdapter<String>(this,R.layout.style_spinner,genderRegistration)
        gender.adapter = arrayAdapter4
        gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Toast.makeText(applicationContext, "selected day is = "+yearsRegistration[p2], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val pronoun= findViewById<Spinner>(R.id.pronoun)
        val arrayAdapter5 = ArrayAdapter<String>(this,R.layout.style_spinner, pronounRegistration)
        pronoun.adapter = arrayAdapter5
        pronoun.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Toast.makeText(applicationContext, "selected day is = "+yearsRegistration[p2], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


        //Función para enviar los datos registrados
        binding.registerButton.setOnClickListener {
            val nombre = binding.nameRegistration.text.toString()
            val apellidoPaterno = binding.lastNameFRegistration.text.toString()
            val apellidoMaterno = binding.lastNameMRegistration.text.toString()
            val DD_registration = binding.spinner.selectedItem.toString()
            val MM_registration = binding.spinner2.selectedItem.toString()
            val AAAA_registration = binding.spinner3.selectedItem.toString()
            val gende = binding.gender.selectedItem.toString()
            val pronou = binding.pronoun.selectedItem.toString()
            val rol = "user"


            if (nombre.isNotEmpty() && apellidoPaterno.isNotEmpty() && apellidoMaterno.isNotEmpty() && DD_registration.isNotEmpty() && MM_registration.isNotEmpty() && AAAA_registration.isNotEmpty() && gende.isNotEmpty() && pronou.isNotEmpty() && rol.isNotEmpty()) {
                registrarUsuario(
                    nombre,
                    apellidoPaterno,
                    apellidoMaterno,
                    DD_registration,
                    MM_registration,
                    AAAA_registration,
                    gende,
                    pronou,
                    rol
                )

            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageLoad.setOnClickListener {
            fileManager()
        }


    } //cierre de área de métodos y atributos

    //Funciones para regsitro de datos en fireStore
    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        } else {

        }
    }

    private fun registrarUsuario(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        DD_registration: String,
        MM_registration: String,
        AAAA_registration: String,
        gende: String,
        pronou: String,
        rol: String
    ) {
        val user = auth.currentUser
        val uid = user!!.uid
        val map = hashMapOf(
            "nombre" to nombre,
            "apellidoPaterno" to apellidoPaterno,
            "apellidoMaterno" to apellidoMaterno,
            "DD_registration" to DD_registration,
            "MM_registration" to MM_registration,
            "AAAA_registration" to AAAA_registration,
            "gender" to gende,
            "pronoun" to pronou,
            "rol" to rol
        )

        val db = Firebase.firestore

        db.collection("userData").document(auth.currentUser?.email.toString()).set(map).addOnSuccessListener {
            infoUser()
            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()

        }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Fallo al guardar la informacion",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    private fun infoUser() {
            val intent = Intent(this, AllSetActivity::class.java)
            this.startActivity(intent)
    }


    private fun reload() {

    }

    //Funciones para imagenes de usuario con Glide
    private fun fileManager() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, fileResult)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == fileResult) {
            if (resultCode == RESULT_OK && data != null) {
                val uri = data.data
                uri?.let { imageUpload(it) }
            }
        }
    }

    private fun imageUpload(mUri: Uri) {
        val user = auth.currentUser
        val folder: StorageReference = FirebaseStorage.getInstance().reference.child("Users")
        val fileName: StorageReference = folder.child("img"+auth.currentUser?.email.toString())
        fileName.putFile(mUri).addOnSuccessListener {
            fileName.downloadUrl.addOnSuccessListener { uri ->
                val profileUpdates = userProfileChangeRequest {
                    photoUri = Uri.parse(uri.toString())
                }
            }
        }
    }



} //cierre de la clase
