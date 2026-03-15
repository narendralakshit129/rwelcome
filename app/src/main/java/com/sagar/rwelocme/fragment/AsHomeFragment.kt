package com.sagar.rwelocme.fragment

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagar.rwelocme.MainActivity
import com.sagar.rwelocme.R
import com.sagar.rwelocme.activity.CoupleRankingActivity
import com.sagar.rwelocme.activity.FamilyRankingActivity
import com.sagar.rwelocme.activity.UserRankingActivity
import com.sagar.rwelocme.adapter.HomeUserAdapter
import com.sagar.rwelocme.adapter.PostAdapter
import com.sagar.rwelocme.model.HomeUserModel
import com.sagar.rwelocme.model.PostModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AsHomeFragment : Fragment() {
    lateinit var tvFollowing: TextView
    lateinit var tvPopular: TextView
    lateinit var tvHot: TextView
    lateinit var btnRanking: TextView
    lateinit var btnFamily: TextView
    lateinit var btnCouple: TextView


    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeUserAdapter: HomeUserAdapter
    private val homeUserList = ArrayList<HomeUserModel>()

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

        val view = inflater.inflate(R.layout.fragment_as_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerHome)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        loadDummyData()

        tvFollowing = view.findViewById<TextView>(R.id.tabFollowing)
        tvPopular = view.findViewById<TextView>(R.id.tabPopular)
        tvHot= view.findViewById<TextView>(R.id.tabHot)

        btnRanking= view.findViewById<TextView>(R.id.btn_ranking)
        btnFamily= view.findViewById<TextView>(R.id.btn_family)
        btnCouple= view.findViewById<TextView>(R.id.btn_couple)

        ViewCompat.getWindowInsetsController(view)?.apply {
            hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        requireActivity().window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        homeUserAdapter = HomeUserAdapter(homeUserList)
        recyclerView.adapter = homeUserAdapter
        setupTabs()
        return view
    }

    private fun setupTabs() {

        fun resetTabs() {
            // Reset all tabs to normal
            tvFollowing.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            tvPopular.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            tvHot.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            tvFollowing.setTypeface(null, Typeface.NORMAL)
            tvPopular.setTypeface(null, Typeface.NORMAL)
            tvHot.setTypeface(null, Typeface.NORMAL)
        }

        fun selectTab(selected: TextView) {
            resetTabs()

            // Selected tab → Blue + Bold
            selected.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
            selected.setTypeface(null, Typeface.BOLD)
        }

        // Default selected
        selectTab(tvFollowing)

        // Click listeners
        tvFollowing.setOnClickListener {
            selectTab(tvFollowing)
        }

        tvPopular.setOnClickListener {
            selectTab(tvPopular)
        }

        tvHot.setOnClickListener {
            selectTab(tvHot)
        }

        btnRanking.setOnClickListener {
            val intent = Intent(requireActivity(), UserRankingActivity::class.java)
            startActivity(intent)
        }

        btnFamily . setOnClickListener {
            val intent = Intent(requireActivity(), FamilyRankingActivity::class.java)
            startActivity(intent)
        }

        btnCouple . setOnClickListener {
            val intent = Intent(requireActivity(), CoupleRankingActivity::class.java)
            startActivity(intent)
        }
    }


    private fun loadDummyData() {
        homeUserList.clear()

        homeUserList.add(HomeUserModel("Customer Support", "Lv. 82", "Diamond", 1, "https://i.pravatar.cc/150?img=1"))

        homeUserList.add(  HomeUserModel("GROW AGENCY", "Lv. 44", "Gold", 5, "https://i.pravatar.cc/150?img=2"),)

        homeUserList.add(  HomeUserModel("BD RAKIB ", "Lv. 30", "Platinum", 8, "https://i.pravatar.cc/150?img=3"), )

        homeUserList.add( HomeUserModel("NAVIN", "Lv. 10", "Platinum", 4, "https://i.pravatar.cc/150?img=4"),)

        homeUserList.add( HomeUserModel("ZEHER", "Lv. 12", "Platinum", 6, "https://i.pravatar.cc/150?img=5"))

        homeUserList.add( HomeUserModel("SK Sharma", "Lv. 12", "Diamond", 6, "https://i.pravatar.cc/150?img=6"))

        homeUserList.add( HomeUserModel("Pankaj Sharma", "Lv. 14", "Platinum", 12, "https://i.pravatar.cc/150?img=7"))

        homeUserList.add( HomeUserModel("Amit Sharma", "Lv. 16", "Gold", 8, "https://i.pravatar.cc/150?img=8"))

        homeUserList.add( HomeUserModel("Narendra Kumar", "Lv. 21", "Platinum", 6, "https://i.pravatar.cc/150?img=9"))

        homeUserList.add( HomeUserModel(" Sagar ", "Lv. 18", "Diamond", 21, "https://i.pravatar.cc/150?img=10"))

        homeUserList.add( HomeUserModel("Neha", "Lv. 13", "Gold", 31, "https://i.pravatar.cc/150?img=11"))

    }
}