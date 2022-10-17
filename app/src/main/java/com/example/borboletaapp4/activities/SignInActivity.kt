package com.example.borboletaapp4.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.borboletaapp4.databinding.ActivitySignInBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth //Autenticación normal
    private lateinit var mAuth: FirebaseAuth //Autenticación con google

    private lateinit var binding: ActivitySignInBinding //normal y google
    private val Google_SIGN_IN = 100

    //callback de facebook
    var callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater) //google
        setContentView(binding.root)
        //setContentView(abinding.root) //confirmacion con google
        //auth = Firebase.auth

        //Inicializar
        auth = FirebaseAuth.getInstance() //checar si dejar como mAuth o auth

        //Función Botones
        boton()

        //variables logueo de facebook
        var TAG=""
        var facebook = findViewById<ImageView>(R.id.facebook)

        binding.signInAppCompatButton.setOnClickListener {
            val mEmail = binding.emailEditText.text.toString()
            val mPassword = binding.passwordEditText.text.toString()

            when {
                mPassword.isEmpty() || mEmail.isEmpty() -> {
                    Toast.makeText(this, "Email o contraseña o incorrectos.",
                        Toast.LENGTH_SHORT).show()
                }
                else -> {
                    signIn(mEmail, mPassword)
                }
            }
        }

        binding.signInButtom.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            this.startActivity(intent)
        }

        binding.recoveryAccountTextView.setOnClickListener {
            val intent = Intent(this, AccountRecoveryActivity::class.java)
            this.startActivity(intent)
        }

        //Funciones de login con facebook
        facebook.setOnClickListener {

            if(userLoggedIn()){
                Firebase.auth.signOut()
            }
            else {
                LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile","email"))
            }
        }
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$result")
                handleFacebookAccessToken(result.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
            }
        })


    }

    private fun userLoggedIn(): Boolean {
        return auth.currentUser!=null && AccessToken.getCurrentAccessToken()!!.isExpired
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            if(currentUser.isEmailVerified){
                reload()
            } else {
                val intent = Intent(this, CheckEmailActivity::class.java)
                this.startActivity(intent)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    reload()
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Email o contraseña o incorrectos.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    //Posible falla futura, pero igual y no pasa nada
    private fun reload() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

    //Funcion botones
    private fun boton() {
        binding.imgGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this,googleConf)
            val signInIntent = googleClient.signInIntent
            startActivityForResult(signInIntent, Google_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Google_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                //Google Sign In was successful, authenticate with FireBase+
                val account = task.getResult(ApiException::class.java)!!
                if (account != null) {
                    Log.d("Tag", "firebasegoogleid $account.id")
                    firebaseAuthWithGoogle(account.idToken!!)
                } else {
                    Toast.makeText(this, "Correo no existe", Toast.LENGTH_LONG).show()
                }
            } catch (e: ApiException) {
                Log.w("Tag", "Google sign in failed $e")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //Sig in success, update UI with the signed-in user's information
                    Log.d("Tag", "sigInWithCredential:success")
                    val user = auth.currentUser?.email.toString()
                    login(user)
                }else{
                    //if sign in fails, display a message to the user
                    Log.w("Tag","signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "No se pudo loguear", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    startActivity(Intent(this,RegistrationActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


}
