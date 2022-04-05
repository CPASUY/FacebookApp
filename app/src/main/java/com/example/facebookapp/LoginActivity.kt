package com.example.facebookapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val userAlfa =  User("Alfa", "alfa@gmail.com","aplicacionesmoviles")
        val userBeta =  User("beta", "beta@gmail.com","aplicacionesmoviles")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButtonId.setOnClickListener{

            val username=editTextTextEmailAddress.text.toString()
            val password=editTextTextPassword.text.toString()
            if(username.equals("alfa@gmail.com") && password.equals("aplicacionesmoviles") || username.equals("beta@gmail.com") && password.equals("aplicacionesmoviles")) {
                val intent= Intent(this, MainActivity::class.java)
                if(username.equals("alfa@gmail.com")){
                    intent.putExtra("user", Gson().toJson(userAlfa))
                    startActivity(intent)
                }else{
                    intent.putExtra("user", Gson().toJson(userBeta))
                    startActivity(intent)
                }

            }
            else{
                Toast.makeText(this.baseContext,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show()
            }
        }

    }

}