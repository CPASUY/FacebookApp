package com.example.facebookapp

class Post {
    var id:String
    var date: String
    var name: String
    var description:String
    //val icon: Int
    //val imagePost: Int

    constructor(id:String,name:String,date:String,description:String/*,icon:Int,imagePost:Int*/){
        this.id=id
        this.name=name
        this.date=date
        this.description=description
        //this.icon=icon
       // this.imagePost=imagePost
    }
}