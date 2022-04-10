package com.example.facebookapp

import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {
    private var posts = ArrayList<Post>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder{
        var inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.postrow,parent,false)
        val postViewHolder = PostViewHolder(view)
        return postViewHolder
    }

    override fun onBindViewHolder(holder:PostViewHolder, position: Int) {
        val post= posts[position]
        holder.nameRow.text = post.name
        holder.dateRow.text = post.date
        holder.descriptionRow.text = post.description
        holder.imagePostRow.setImageURI(Uri.parse(post.imagePost))
    }
    fun onPause(sharedPreferences: SharedPreferences){
        val json = Gson().toJson(posts)
        Log.e("postsss",json.toString())
        sharedPreferences.edit().putString("current",json).apply()
    }
    fun addPost(post:Post){
        posts.add(post)
        Log.e(">>SIZEEEEEE>" ,post.id.toString())
        Log.e(">>SIZEEEEEE>" ,getItemCount().toString(),)

    }
    override fun getItemCount(): Int {
        return posts.size
    }
    fun onResume(sharedPreferences: SharedPreferences){

        var json = sharedPreferences.getString("currentPosts","NO_DATA")
        if(json != "NO_DATA"){
            if(posts.size==0){
                val array = Gson().fromJson(json.toString(),Array<Post>::class.java)
                val oldPosts = ArrayList(array.toMutableList())
                if(oldPosts.isNotEmpty()){
                    posts = oldPosts
                }
            }
        }
    }
}