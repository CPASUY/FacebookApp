package com.example.facebookapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButtonId.setOnClickListener{

            val username=editTextTextEmailAddress.text.toString()
            val password=editTextTextPassword.text.toString()
            if(username.equals("alfa@gmail.com") && password.equals("aplicacionesmoviles") || username.equals("beta@gmail.com") && password.equals("aplicacionesmoviles")) {
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }

}