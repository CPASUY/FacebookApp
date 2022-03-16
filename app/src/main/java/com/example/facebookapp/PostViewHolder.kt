package com.example.facebookapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val nameRow: TextView = itemView.findViewById(R.id.namePost)
    val dateRow:TextView = itemView.findViewById(R.id.datePost)
    val imageProfileRow: ImageView = itemView.findViewById(R.id.imageProfilePost)
    val descriptionRow: TextView = itemView.findViewById(R.id.descriptionPost)
    val imagePostRow: ImageView  = itemView.findViewById(R.id.imagePost)
}