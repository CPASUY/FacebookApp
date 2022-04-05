package com.example.facebookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.facebookapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var postFragment: PublishFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userActual = intent.getStringExtra("user")
        var user = Gson().fromJson(userActual, User::class.java)
        homeFragment = HomeFragment.newInstance()
        postFragment = PublishFragment.newInstance(user)
        profileFragment = ProfileFragment.newInstance(user)
        //Suscripcion
        postFragment.listener = homeFragment


        showFragment(homeFragment)
        binding.navigator.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId== R.id.homeItem){
                showFragment(homeFragment)
            }else if(menuItem.itemId== R.id.postItem){
                showFragment(postFragment)
            }
            else if(menuItem.itemId== R.id.profileItem){
                showFragment(profileFragment)
            }
            true
        }
    }
    fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }
}