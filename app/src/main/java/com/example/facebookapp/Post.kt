package com.example.facebookapp

import java.util.*

class Post {
    var id:String
    var date: String
    var name: String
    var description:String
    var city:String
    //val icon: Int
    val imagePost: String

    constructor(name:String,date:String,description:String,city:String,imagePost: String,/*,icon:Int,imagePost:Int*/){
        this.id=  UUID.randomUUID().toString()
        this.name=name
        this.date=date
        this.description=description
        this.city=city
        this.imagePost=imagePost

        //this.icon=icon
       // this.imagePost=imagePost
    }
}