package com.example.facebookapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {
    private val posts = ArrayList<Post>()
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
    }
    fun addPost(post:Post){
        posts.add(post)
    }
    override fun getItemCount(): Int {
        return posts.size
    }

}