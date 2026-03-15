package com.sagar.rwelocme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.model.ChairModel


class RoomChairAdapter(
    private val roomList: List<ChairModel>,
    private val onItemClick: (ChairModel) -> Unit
) : RecyclerView.Adapter<RoomChairAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivChairIcon: FrameLayout = itemView.findViewById(R.id.ic_chair)
        val tvChairNum: TextView = itemView.findViewById(R.id.txt_chair_number)

        fun bind(chair: ChairModel) {

            tvChairNum.text = chair.chairNum
            ivChairIcon.setOnClickListener {
                onItemClick(chair)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_room_chairs, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(roomList[position])
    }

    override fun getItemCount(): Int = roomList.size
}
