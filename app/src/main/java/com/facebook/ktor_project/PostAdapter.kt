package com.facebook.ktor_project

import DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PostAdapter(private var postList: List<Post?>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {



    // ViewHolder class to hold references to views for each item
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Add references to views in the item_post.xml layout
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val yearTextView: TextView = itemView.findViewById(R.id.yearTextView)
        val languageTextView: TextView = itemView.findViewById(R.id.languageTextView)
        val comedyTextView: TextView = itemView.findViewById(R.id.comedyTextView)
        val DramedyTextView: TextView = itemView.findViewById(R.id.DramedyTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
         // Add other views as needed
    }

    // Inflate the item_post.xml layout for each item and return a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemview, parent, false)
        return PostViewHolder(itemView)
    }

    // Bind data to the views in each ViewHolder
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = postList[position]

        // Bind data to views
        Log.d("PostAdapter", "Binding data for position $position: $currentPost")
        if (currentPost != null) {
            holder.titleTextView.text = currentPost.title
            holder.yearTextView.text = DateUtils.extractYearFromDate(currentPost.date)+" ."
            holder.languageTextView.text = currentPost.content_language.toUpperCase()+" ."
            holder.ratingTextView.text ="U/A 13+"+" ."
            holder.comedyTextView.text = currentPost.primary_category+" ."
            holder.DramedyTextView.text = currentPost.content_type.toUpperCase()+" ."
            holder.descriptionTextView.text = currentPost.description
            // Using Coil to load the image into the ImageView
            holder.imageView.load(currentPost.xhd_image) {
                crossfade(true) // Optional: Enable crossfade for a smooth transition
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return postList.size
    }
    fun updateData(newList: List<Post?>) {
        postList = newList.filterNotNull()
        notifyDataSetChanged()
        Log.d("PostAdapter", "Data updated: ${postList.size}")

    }

}
