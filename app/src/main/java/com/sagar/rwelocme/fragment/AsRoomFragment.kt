package com.sagar.rwelocme.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagar.rwelocme.MainActivity
import com.sagar.rwelocme.R
import com.sagar.rwelocme.activity.CreateRoomActivity
import com.sagar.rwelocme.adapter.PostAdapter
import com.sagar.rwelocme.adapter.RoomAdapter
import com.sagar.rwelocme.model.PostModel
import com.sagar.rwelocme.model.RoomModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AsRoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AsRoomFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var roomAdapter: RoomAdapter
    private lateinit var ivAddRoom: ImageView
    private val roomsList = ArrayList<RoomModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_as_room, container, false)
        requireActivity().window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        ivAddRoom = view.findViewById(R.id.iv_add_room)
        recyclerView = view.findViewById(R.id.recyclerRoomsTalk)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        loadDummyData()

        ivAddRoom.setOnClickListener {
            val intent = Intent(requireContext(), CreateRoomActivity::class.java)
            startActivity(intent)
        }


        roomAdapter = RoomAdapter(roomsList) { room ->
            Toast.makeText(requireContext(), "Clicked: ${room.roomName}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = roomAdapter

        return view
    }

    private fun loadDummyData() {
        roomsList.clear()

        roomsList.add(
        RoomModel(
            roomName = "Let's Talk",
            description = "Join and talk ideas",
            members = 12,
            type = "Chat",
            iconRes = R.drawable.ic_mic
        ))

        roomsList.add(
        RoomModel(
            roomName = "Snakes Arena",
            description = "Play and chill",
            members = 8,
            type = "Snakes and Ladders",
            iconRes = R.drawable.ic_mic
        ))

    }

}