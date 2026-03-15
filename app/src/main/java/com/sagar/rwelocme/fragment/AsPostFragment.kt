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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.activity.CoupleRankingActivity
import com.sagar.rwelocme.activity.FamilyRankingActivity
import com.sagar.rwelocme.activity.UserRankingActivity
import com.sagar.rwelocme.adapter.PostAdapter
import com.sagar.rwelocme.model.PostModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AsPostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AsPostFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var tvFollowing: TextView
    lateinit var tvTrending: TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private val postList = ArrayList<PostModel>()

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


        val view = inflater.inflate(R.layout.fragment_as_post, container, false)

        requireActivity().window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        tvTrending = view.findViewById<TextView>(R.id.tvTrending)
        tvFollowing = view.findViewById<TextView>(R.id.tvFollowing)


        recyclerView = view.findViewById(R.id.recyclerPosts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        loadDummyData()

        postAdapter = PostAdapter(postList)
        recyclerView.adapter = postAdapter
        setupTabs()
        return view
    }

    private fun setupTabs() {

        fun resetTabs() {
            // Reset all tabs to normal
            tvTrending.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            tvFollowing.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            tvTrending.setTypeface(null, Typeface.NORMAL)
            tvFollowing.setTypeface(null, Typeface.NORMAL)
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
        tvTrending.setOnClickListener {
            selectTab(tvTrending)
        }

        tvFollowing.setOnClickListener {
            selectTab(tvFollowing)
        }




    }


    private fun loadDummyData() {
        postList.clear()

        postList.add(
            PostModel(
                "Prashant Piyush",
                "Wed, 13 Mar",
                "LIVING MIGHT MEAN TAKING CHANCES, BUT THEY'RE WORTH TAKING",
                "https://picsum.photos/500/306",
                88, 1, 0
            )
        )

        postList.add(
            PostModel(
                "Narendra Kumar",
                "Wed, 11 Nov",
                "ADOPT THE PACE OF NATURE. HER SECRET IS PATIENCE",
                "https://picsum.photos/500/300",
                88, 1, 0
            )
        )

        postList.add(
            PostModel(
                "Mr Riyaz",
                "Mon, 11 Mar",
                "MAIN TERA DIL NA TODUNGA...",
                "https://picsum.photos/500/301",
                243, 2, 5
            )
        )

        postList.add(
            PostModel(
                "Ankit Sharma",
                "Tue, 12 Mar",
                "THE COUNTRY’S OLDEST NATIONAL PARK (EST. 1936) AND A PREMIER DESTINATION FOR BENGAL TIGERS, ESTABLISHED TO PROTECT ENDANGERED SPECIES",
                "https://picsum.photos/500/302",
                120, 4, 1
            )
        )

        postList.add(
            PostModel(
                "Neha Singh",
                "Wed, 13 Mar",
                "LIVE IN THE SUNSHINE, SWIM THE SEA, DRINK THE WILD AIR.",
                "https://picsum.photos/500/306",
                88, 1, 0
            )
        )
        postList.add(
            PostModel(
                "Rajeev Singh",
                "Wed, 13 Mar",
                "YOU HAVE TO BELIEVE IN YOURSELF WHEN NO ONE ELSE DOES.",
                "https://picsum.photos/500/305",
                88, 1, 0
            )
        )

    }
}