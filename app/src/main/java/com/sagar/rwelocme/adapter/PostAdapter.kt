package com.sagar.rwelocme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sagar.rwelocme.R
import com.sagar.rwelocme.model.PostModel

class PostAdapter(private val list: List<PostModel>) :
    RecyclerView.Adapter<PostAdapter.PostVH>() {

    class PostVH(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.tvName)
        val date = view.findViewById<TextView>(R.id.tvDate)
        val msg = view.findViewById<TextView>(R.id.tvPostText)
        val img = view.findViewById<ImageView>(R.id.imgPost)
        val likes = view.findViewById<TextView>(R.id.tvLikes)
        val comments = view.findViewById<TextView>(R.id.tvComments)
        val shares = view.findViewById<TextView>(R.id.tvShare)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_post, parent, false)
        return PostVH(v)
    }

    override fun onBindViewHolder(h: PostVH, pos: Int) {
        val p = list[pos]
        h.name.text = p.userName
        h.date.text = p.date
        h.msg.text = p.message
        h.likes.text = "${p.likes}"
        h.comments.text = "${p.comments} Comments"
        h.shares.text = "${p.shares} Shares"

        Glide.with(h.itemView.context)
            .load(p.imageUrl)
            .placeholder(R.drawable.user_profiles_background)
            .error(R.drawable.user_profiles_background)
            .into(h.img)

       /* Glide.with(h..context)
            .load(p.imageUrl)
            .placeholder(R.drawable.ic_profile_placeholder)
            .error(R.drawable.ic_profile_placeholder)
            .into(imageView)*/
    }

    override fun getItemCount() = list.size
}
