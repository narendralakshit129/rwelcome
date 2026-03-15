package com.sagar.rwelocme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.model.UserRankModel

class UserRankAdapter (
    private val list: List<UserRankModel>
) : RecyclerView.Adapter<UserRankAdapter.UserRankViewHolder>() {

    inner class UserRankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtRank: TextView = itemView.findViewById(R.id.tv_rank)

        val imgUserFirst: ShapeableImageView = itemView.findViewById(R.id.img_user)
        val txtNameFirst: TextView = itemView.findViewById(R.id.tv_user_name)

        val txtCoins: TextView = itemView.findViewById(R.id.tv_coins)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRankViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_user_rank, parent, false)
        return UserRankViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserRankViewHolder, position: Int) {
        val item = list[position]

        holder.txtRank.text = item.rank.toString()
        holder.txtNameFirst.text = item.name
        holder.txtCoins.text = item.coin


        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.mipmap.user_profile)
            .error(R.mipmap.user_profile)
            .into(holder.imgUserFirst)


    }

    override fun getItemCount(): Int = list.size
}