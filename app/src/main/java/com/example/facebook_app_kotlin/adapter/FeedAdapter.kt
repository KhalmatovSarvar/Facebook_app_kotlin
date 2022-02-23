package com.example.facebook_app_kotlin.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebook_app_kotlin.CreatePostActivity
import com.example.facebook_app_kotlin.MainActivity
import com.example.facebook_app_kotlin.R
import com.example.facebook_app_kotlin.model.Feed
import com.example.facebook_app_kotlin.model.Link
import com.example.facebook_app_kotlin.model.Story
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class FeedAdapter(var activity: MainActivity, var items: ArrayList<Feed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var TYPE_HEADER = 0
    var TYPE_STORY = 1
    var TYPE_POST = 2
    var TYPE_POST_PHOTO_ADD = 3
    var TYPE_LINK = 4

    private var urlAddress: String? = null

    fun addItem(newItem: Link) {
        items.add(Feed(newItem))
        Log.d("RRR", "----updated---- ${newItem.title}")
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        var item = items[position]
        return when {
            item.isHeader -> TYPE_HEADER
            item.stories.size > 0 -> TYPE_STORY
            item.link != null -> TYPE_LINK
            item.post != null -> TYPE_POST
            else -> TYPE_POST_PHOTO_ADD
        }

    }

    private fun containsLink(input: String): Boolean {
        var result = false
        val parts = input.split("\\s+").toTypedArray()
        for (item in parts) {
            if (Patterns.WEB_URL.matcher(item).matches()) {
                result = true
                urlAddress = item
                break
            }
        }
        return result
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_header, parent, false)
            return HeaderViewHHolder(view)
        } else if (viewType == TYPE_STORY) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false)
            return StoryViewHHolder(view)
        } else if (viewType == TYPE_POST) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
            return PostsViewHHolder(view)
        } else if (viewType == TYPE_POST_PHOTO_ADD) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_posts_multiple_photo, parent, false)
            return PostsAddPhotoViewHHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.link_layout, parent, false)
            return LinkLayoutViewHHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = items[position]
        if (holder is PostsViewHHolder) {
            var profile = holder.profile
            var fullname = holder.fullname
            var post = holder.post
            var date = holder.date

            profile.setImageResource(item.post!!.profile)
            fullname.text = item.post!!.fullname
            Picasso.get().load(item.post!!.post).into(post);

//             photo.setImageResource(item.post!!.photo)
            date.text = item.post!!.date


        }
        if (holder is StoryViewHHolder) {
            var recycler_story = holder.recyclerViewStory
            recycler_story.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            refreshStoryAdapter(item.stories, recycler_story)
        }


        if (holder is PostsAddPhotoViewHHolder) {
            var profile = holder.profile
            var fullname = holder.fullname
            var date = holder.date
            var photo1 = holder.photo1
            var photo2 = holder.photo2
            var photo3 = holder.photo3
            var photo4 = holder.photo4
            var photo5 = holder.photo5


            profile.setImageResource(item.photos!!.profile)
            fullname.text = item.photos!!.title
            date.text = item.photos!!.date
            photo1.setImageResource(item.photos!!.photo1)
            photo2.setImageResource(item.photos!!.photo2)
            photo3.setImageResource(item.photos!!.photo3)
            photo4.setImageResource(item.photos!!.photo4)
            photo5.setImageResource(item.photos!!.photo5)
        }
        if (holder is HeaderViewHHolder) {
            var edit_text = holder.edit_text

            edit_text.setOnClickListener {

                activity.openCreateActivity()
            }

        }

        if (holder is LinkLayoutViewHHolder) {
            var tv_link = holder.tv_link
            var iv_image = holder.iv_image
            var tv_title = holder.tv_title
            var tv_description = holder.tv_description

            tv_link.text = item.link?.link
            tv_description.text = item.link?.description
            tv_title.text = item.link?.title
            Picasso.get().load(item.link!!.image).into(iv_image);
        }
    }

    private fun refreshStoryAdapter(stories: ArrayList<Story>, recyclerviewStory: RecyclerView) {
        val adapter = StoryAdapter(activity, stories)
        recyclerviewStory.adapter = adapter
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class LinkLayoutViewHHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tv_link: TextView = view.findViewById(R.id.link_container)
    var iv_image: ShapeableImageView = view.findViewById(R.id.link_image)
    var tv_title: TextView = view.findViewById(R.id.title_link)
    var tv_description: TextView = view.findViewById(R.id.description_link_post)
}

class PostsAddPhotoViewHHolder(view: View) : RecyclerView.ViewHolder(view) {
    var profile: ShapeableImageView
    var fullname: TextView
    var date: TextView
    var photo1: ShapeableImageView
    var photo2: ShapeableImageView
    var photo3: ShapeableImageView
    var photo4: ShapeableImageView
    var photo5: ShapeableImageView

    init {
        profile = view.findViewById(R.id.iv_profile_post_add_photos)
        fullname = view.findViewById(R.id.tv_fullname_post_add_photos)
        date = view.findViewById(R.id.tv_time_post_add_posts)
        photo1 = view.findViewById(R.id.photo_1)
        photo2 = view.findViewById(R.id.photo_2)
        photo3 = view.findViewById(R.id.photo_3)
        photo4 = view.findViewById(R.id.photo_4)
        photo5 = view.findViewById(R.id.photo_5)
    }

}

class HeaderViewHHolder(view: View) : RecyclerView.ViewHolder(view) {
    var edit_text: TextView = view.findViewById(R.id.tv_add_post)

}

class StoryViewHHolder(view: View) : RecyclerView.ViewHolder(view) {
    var recyclerViewStory = view.findViewById<RecyclerView>(R.id.rv_stories)
}

class PostsViewHHolder(view: View) : RecyclerView.ViewHolder(view) {
    var profile: ShapeableImageView
    var fullname: TextView
    var post: ShapeableImageView
    var date: TextView

    init {
        profile = view.findViewById(R.id.iv_profile_post)
        fullname = view.findViewById(R.id.tv_fullname_post)
        post = view.findViewById(R.id.iv_photo_post)
        date = view.findViewById(R.id.tv_time_post)
    }

}
