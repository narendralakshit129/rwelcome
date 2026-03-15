package com.sagar.rwelocme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.model.RoomModel

class RoomAdapter(
    private val roomList: List<RoomModel>,
    private val onItemClick: (RoomModel) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        val tvRoomName: TextView = itemView.findViewById(R.id.tvRoomName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvMeta: TextView = itemView.findViewById(R.id.tvMeta)

        fun bind(room: RoomModel) {
            ivIcon.setImageResource(room.iconRes)
            tvRoomName.text = room.roomName
            tvDescription.text = room.description
            tvMeta.text = "${room.members} members • ${room.type}"

            itemView.setOnClickListener {
                onItemClick(room)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_room_items, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(roomList[position])
    }

    override fun getItemCount(): Int = roomList.size
}
