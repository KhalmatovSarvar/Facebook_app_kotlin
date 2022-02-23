package com.example.facebook_app_kotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook_app_kotlin.adapter.FeedAdapter
import com.example.facebook_app_kotlin.model.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var tabLayout: TabLayout
    lateinit var result: Link
    lateinit var adapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.rv_posts)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        tabLayout = findViewById(R.id.tab_layout)

        getIconsChanged()

        refreshFeeds(getFeeds())

    }


    private fun getIconsChanged() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> tab.setIcon(R.drawable.ic_home_selected)
                    1 -> tab.setIcon(R.drawable.ic_people_selected)
                    2 -> tab.setIcon(R.drawable.ic_videos_selected)
                    3 -> tab.setIcon(R.drawable.ic_person_selected)
                    4 -> tab.setIcon(R.drawable.ic_notifications_selected)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> tab.setIcon(R.drawable.ic_home)
                    1 -> tab.setIcon(R.drawable.ic_people)
                    2 -> tab.setIcon(R.drawable.ic_videos)
                    3 -> tab.setIcon(R.drawable.ic_person)
                    4 -> tab.setIcon(R.drawable.ic_notifications)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }

    private fun refreshFeeds(feeds: ArrayList<Feed>) {
        adapter = FeedAdapter(this, feeds)
        recyclerView.adapter = adapter
    }

    private fun getFeeds(): ArrayList<Feed> {
        var stories: ArrayList<Story> = ArrayList()

        stories.add(Story(R.drawable.sarvar, "Khalmatov Sarvar", R.drawable.nature_2))
        stories.add(Story(R.drawable.sherzod, "Jo`rabekov Sherzod", R.drawable.nature_4))
        stories.add(Story(R.drawable.tohir, "Rahmatullayev Tohir", R.drawable.nature_3))
        stories.add(Story(R.drawable.sherzod, "Jo`rabekov Sherzod", R.drawable.nature_5))
        stories.add(Story(R.drawable.sarvar, "Khalmatov Sarvar", R.drawable.nature_6))
        stories.add(Story(R.drawable.tohir, "Rahmatullayev Tohir", R.drawable.nature_3))
        stories.add(Story(R.drawable.sherzod, "Jo`rabekov Sherzod", R.drawable.nature_2))
        stories.add(Story(R.drawable.tohir, "Rahmatullayev Tohir", R.drawable.nature_4))
        stories.add(Story(R.drawable.sherzod, "Jo`rabekov Sherzod", R.drawable.nature_6))

        var feeds: ArrayList<Feed> = ArrayList()

        var photos: ArrayList<PostAddPhotos> = ArrayList<PostAddPhotos>()
        photos.add(
            PostAddPhotos(
                R.drawable.tohir, "Tabiat qo`yniga marhamat", "2", R.drawable.nature_6,
                R.drawable.nature_3, R.drawable.nature_5, R.drawable.nature_4, R.drawable.nature_2
            )
        )

        feeds.add(Feed())
        feeds.add(Feed(stories))

        feeds.add(
            Feed(
                Post(
                    R.drawable.sherzod,
                    "Sherzod",
                    "https://images.unsplash.com/photo-1645528099962-235389d6aabc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzfHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
                    "1day"
                )
            )
        )
        feeds.add(
            Feed(
                Post(
                    R.drawable.sarvar,
                    "Sarvar",
                    "https://images.unsplash.com/photo-1645539988396-1d65a85fbb4e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw2MXx8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60",
                    "1day"
                )
            )
        )
        feeds.add(
            Feed(
                Post(
                    R.drawable.tohir,
                    "Tohir",
                    "https://images.unsplash.com/photo-1645517455143-b8039ce13f7f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxMTV8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
                    "1day"
                )
            )
        )
        feeds.add(
            Feed(
                PostAddPhotos(
                    R.drawable.tohir,
                    "Tabiat qo`yniga marhamat",
                    "2",
                    R.drawable.nature_6,
                    R.drawable.nature_3,
                    R.drawable.nature_5,
                    R.drawable.nature_4,
                    R.drawable.nature_2
                )
            )
        )
        feeds.add(
            Feed(
                PostAddPhotos(
                    R.drawable.tohir,
                    "Tabiat qo`yniga marhamat",
                    "2",
                    R.drawable.nature_6,
                    R.drawable.nature_3,
                    R.drawable.nature_5,
                    R.drawable.nature_4,
                    R.drawable.nature_2
                )
            )
        )
        feeds.add(
            Feed(
                PostAddPhotos(
                    R.drawable.tohir,
                    "Tabiat qo`yniga marhamat",
                    "2",
                    R.drawable.nature_6,
                    R.drawable.nature_3,
                    R.drawable.nature_5,
                    R.drawable.nature_4,
                    R.drawable.nature_2
                )
            )
        )
        feeds.add(
            Feed(
                PostAddPhotos(
                    R.drawable.tohir,
                    "Tabiat qo`yniga marhamat",
                    "2",
                    R.drawable.nature_6,
                    R.drawable.nature_3,
                    R.drawable.nature_5,
                    R.drawable.nature_4,
                    R.drawable.nature_2
                )
            )
        )


        return feeds
    }

    fun openCreateActivity() {
        val intent = Intent(this, CreatePostActivity::class.java)
        startActivityForResult(intent, -3)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("RRR", "Main --> $requestCode -- $resultCode")
        if (requestCode == -3) {
            Log.d("RRR", "$requestCode -- $resultCode")
            if (resultCode == RESULT_OK) {
                result = data?.getParcelableExtra<Parcelable>("object") as Link
//                result = intent.getParcelableExtra("object")!!
                Log.d("@@@", result.toString())
                adapter.addItem(result)
            }
        }
    }
}