package com.sagar.rwelocme.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.Utils.StorePref
import com.sagar.rwelocme.activity.CreateRoomActivity
import com.sagar.rwelocme.adapter.RoomAdapter
import com.sagar.rwelocme.model.RoomModel
import com.sagar.rwelocme.presentation.viewmodel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AsRoomFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var roomAdapter: RoomAdapter
    private lateinit var ivAddRoom: ImageView
    private val viewModel: RoomViewModel by viewModels()

    private val roomsList = ArrayList<RoomModel>()
    @Inject
    lateinit var pref: StorePref
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_as_room, container, false)

        // Fullscreen (optional)
        requireActivity().window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        initViews(view)
        setupRecycler()
        setupClicks()
        observeState()

        return view
    }

    private fun initViews(view: View) {
        ivAddRoom = view.findViewById(R.id.iv_add_room)
        recyclerView = view.findViewById(R.id.recyclerRoomsTalk)
    }

    private fun setupRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        roomAdapter = RoomAdapter(roomsList) { room ->
            Toast.makeText(
                requireContext(),
                "Clicked: ${room.roomName}",
                Toast.LENGTH_SHORT
            ).show()

            // 👉 later pass roomId here
        }

        recyclerView.adapter = roomAdapter
    }

    private fun setupClicks() {
        ivAddRoom.setOnClickListener {
            startActivity(Intent(requireContext(), CreateRoomActivity::class.java))
        }
    }


    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            val token = pref.getUserToken()
            viewModel.getRooms(token)
        }
    }

    private fun observeState() {

        lifecycleScope.launch {
            launch {
                viewModel.rooms.collect { list ->
                    roomsList.clear()
                    list.forEach {
                        roomsList.add(
                            RoomModel(
                                roomName = it.name,
                                description = it.description ?: "",
                                members = it.memberIds.size,
                                type = it.type ?: "",
                                iconRes = R.drawable.ic_mic
                            )
                        )
                    }

                    roomAdapter.notifyDataSetChanged()
                }
            }
            launch {
                viewModel.isLoading.collect {
                    if (it) {
                        Toast.makeText(
                            requireContext(),
                            "Loading rooms...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            launch {
                viewModel.error.collect {
                    it?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}