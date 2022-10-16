package com.example.borboletaapp4

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.borboletaapp4.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistrationBinding
    lateinit var datePickerDialog: DatePickerDialog

    val daysRegistration = arrayOf("DD","01","02","03","04","05","06","07","08","09","10","11","12","13",
        "14","15","16","17","18","19","20","21","22","23","24","25","26",
        "27","28","29","30","31")

    val monthsRegistration = arrayOf("MM","Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
        "Septiembre","Octubre","Noviembre","Diciembre")

    val yearsRegistration = arrayOf("AAAA","1990","1991","1992","1993","1994","1995","1996","1997","1998",
        "1999","2000")

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

        //Agregar direccionamiento hacia la pantalla de filtrado
        binding.registerButton.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            this.startActivity(intent)
        }

        //Función para enviar los datos registrados
        binding.registerButton.setOnClickListener {
            val nombre = binding.nameRegistration.text.toString()
            val apellidoPaterno = binding.lastNameFRegistration.text.toString()
            val apellidoMaterno = binding.lastNameMRegistration.text.toString()
            //val telefono = binding.spinner.spinner.toString()
            val DD_registration = binding.spinner.selectedItem.toString()
            //val genero = binding.spinner2.text.toString()
            val MM_registration = binding.spinner2.selectedItem.toString()
            //val fechanac = binding.spinner3.text.toString()
            val AAAA_registration = binding.spinner3.selectedItem.toString()
            val gender = binding.gender.selectedItem.toString()
            val pronoun = binding.pronoun.selectedItem.toString()
            val rol = "user"

            if (nombre.isNotEmpty() && apellidoPaterno.isNotEmpty() && apellidoMaterno.isNotEmpty() && DD_registration.isNotEmpty() && MM_registration.isNotEmpty() && AAAA_registration.isNotEmpty() && gender.isNotEmpty() && pronoun.isNotEmpty() ) {
                /*registrarUsuario(
                    nombre,
                    apellidoPaterno,
                    apellidoMaterno,
                    DD_registration,
                    MM_registration,
                    AAAA_registration,
                    gender,
                    pronoun,
                )*/
            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
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
        gender: String,
        pronoun: String,
        rol: String,
    ) {

       /* val password = auth
        val email = auth
        auth.createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) { */


                    val user = auth.currentUser

                    val uid = user!!.uid

                    val map = hashMapOf(
                        "nombre" to nombre,
                        "apellidoPaterno" to apellidoPaterno,
                        "apellidoMaterno" to apellidoMaterno,
                        "DD_registration" to DD_registration,
                        "MM_registration" to MM_registration,
                        "AAAA_registration" to AAAA_registration,
                        "gender" to gender,
                        "pronoun" to pronoun,
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
                /*else {


                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }*/
            //}

    }*/

    private fun infoUser() {
        val infoUserIntent = Intent(this, ConfigurationActivity::class.java)
        startActivity(infoUserIntent)

    }


    private fun reload() {

    }



} //cierre de la clase
