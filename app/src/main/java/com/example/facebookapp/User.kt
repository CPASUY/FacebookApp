package com.example.facebookapp

import java.util.*

data class User (var name:String,var email:String,var password:String){
    var id:String =  UUID.randomUUID().toString()
    var profileImage:String = ""
    var coverImage:String = ""
}