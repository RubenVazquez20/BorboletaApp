package com.example.borboletaapp4

import android.content.Context
import android.content.Intent
import com.example.borboletaapp4.activities.RegistrationActivity
import com.example.borboletaapp4.activities.SignInActivity
import com.google.firebase.auth.FirebaseAuth

fun Context.login(email:String){
    val intent = Intent(this, RegistrationActivity::class.java).apply{
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    startActivity(intent)
}

fun Context.logout(){
    FirebaseAuth.getInstance().signOut()
    val intent = Intent(this, SignInActivity::class.java).apply{
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    startActivity(intent)
}