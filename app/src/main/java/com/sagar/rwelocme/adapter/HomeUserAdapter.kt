package com.sagar.rwelocme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.model.HomeUserModel

class HomeUserAdapter(
    private val list: List<HomeUserModel>
) : RecyclerView.Adapter<HomeUserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgAvatar: ShapeableImageView = itemView.findViewById(R.id.image_avatar)
        val txtName: TextView = itemView.findViewById(R.id.txt_name)
        val txtLevel: TextView = itemView.findViewById(R.id.txt_level)
        val txtBadge: TextView = itemView.findViewById(R.id.txt_badge)
        val txtCount: TextView = itemView.findViewById(R.id.txtVoiceCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_home_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list[position]

        holder.txtName.text = item.name
        holder.txtLevel.text = item.level
        holder.txtBadge.text = item.badge
        holder.txtCount.text = item.count.toString()

        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.mipmap.user_profile)
            .error(R.mipmap.user_profile)
            .into(holder.imgAvatar)
    }

    override fun getItemCount(): Int = list.size
}
