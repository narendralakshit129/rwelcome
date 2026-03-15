package com.sagar.rwelocme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.model.CoupleRankModel


class CoupleRankAdapter(
    private val list: List<CoupleRankModel>
) : RecyclerView.Adapter<CoupleRankAdapter.CoupleRankViewHolder>() {

    inner class CoupleRankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtRank: TextView = itemView.findViewById(R.id.tv_rank)

        val imgUserFirst: ShapeableImageView = itemView.findViewById(R.id.image_user_first)
        val txtNameFirst: TextView = itemView.findViewById(R.id.txt_name_first)

        val imgUserSecond: ShapeableImageView = itemView.findViewById(R.id.image_user_second)
        val txtNameSecond: TextView = itemView.findViewById(R.id.txt_name_second)

        val txtCoins: TextView = itemView.findViewById(R.id.tv_coins)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoupleRankViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_couple_ranking, parent, false)
        return CoupleRankViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoupleRankViewHolder, position: Int) {
        val item = list[position]

        holder.txtRank.text = item.rank.toString()
        holder.txtNameFirst.text = item.name
        holder.txtNameSecond.text = item.name_two
        holder.txtCoins.text = item.coin


        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.mipmap.user_profile)
            .error(R.mipmap.user_profile)
            .into(holder.imgUserFirst)

        Glide.with(holder.itemView.context)
            .load(item.imageUrl_two)
            .placeholder(R.mipmap.user_profile)
            .error(R.mipmap.user_profile)
            .into(holder.imgUserSecond)
    }

    override fun getItemCount(): Int = list.size
}