package com.sagar.rwelocme.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sagar.rwelocme.R
import com.sagar.rwelocme.activity.AsFriendsActivity
import com.sagar.rwelocme.activity.AsSettingActivity
import com.sagar.rwelocme.activity.FamilyCreateJoinActivity
import com.sagar.rwelocme.activity.UpdateProfileActivity
import com.sagar.rwelocme.dialog.AddOptionsBottomSheet

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AsMeProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AsMeProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var icSettings: ImageView
    lateinit var txtFollowers: TextView
    lateinit var txtFollowing: TextView
    lateinit var txtGift: TextView
    lateinit var layoutCreateFamily: LinearLayout
    lateinit var updateProfile: View
    lateinit var ivAddStatus: ImageView


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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_as_me_profile, container, false)

        icSettings = view.findViewById<ImageView>(R.id.ic_settings)

        txtFollowers = view.findViewById<TextView>(R.id.txt_followers)
        txtFollowing = view.findViewById<TextView>(R.id.txt_following)
        txtGift = view.findViewById<TextView>(R.id.txt_gift)
        ivAddStatus = view.findViewById<ImageView>(R.id.iv_add_status)
        layoutCreateFamily = view.findViewById<LinearLayout>(R.id.layour_create_family)

        updateProfile = view.findViewById<LinearLayout>(R.id.update_profile)

        icSettings.setOnClickListener {
            val intent = Intent(requireActivity(), AsSettingActivity::class.java)
            startActivity(intent)
        }

        txtFollowers.setOnClickListener {
            val intent = Intent(requireActivity(), AsFriendsActivity::class.java)
            startActivity(intent)
        }

        txtFollowing.setOnClickListener {
            val intent = Intent(requireActivity(), AsFriendsActivity::class.java)
            startActivity(intent)
        }
        ivAddStatus.setOnClickListener {
            AddOptionsBottomSheet().show(parentFragmentManager, "AddOptionsBottomSheet")
        }

        layoutCreateFamily.setOnClickListener {
            val intent = Intent(requireActivity(), FamilyCreateJoinActivity::class.java)
            startActivity(intent)
        }

        updateProfile.setOnClickListener {
            val intent = Intent(requireActivity(), UpdateProfileActivity::class.java)
            startActivity(intent)
        }
        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AsMeProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AsMeProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}