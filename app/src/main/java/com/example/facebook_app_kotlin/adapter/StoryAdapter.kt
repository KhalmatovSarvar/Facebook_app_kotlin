package com.example.facebook_app_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook_app_kotlin.R
import com.example.facebook_app_kotlin.model.Story
import com.google.android.material.imageview.ShapeableImageView

class StoryAdapter(var context: Context, var items:ArrayList<Story>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var TYPE_HEADER_STORY = 0
    var TYPE_STORY = 1

    override fun getItemViewType(position: Int): Int {
        if(position == 0) return TYPE_HEADER_STORY
        return TYPE_STORY
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_HEADER_STORY){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_add_story,parent,false)
            return  StoriesAddViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story,parent,false)
            return  StoriesViewHolder(view)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is StoriesViewHolder){

            var profile = holder.profile
            var fullname = holder.fullname
            var backGroundStory = holder.backGroundStory

            profile.setImageResource(item.profile)
            fullname.text = item.fullname
            backGroundStory.setImageResource(item.backGroundStory)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class StoriesAddViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}

class StoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var profile:ShapeableImageView
    var fullname:TextView
    var backGroundStory:ShapeableImageView
    init {
        profile = view.findViewById(R.id.iv_profile_story)
        fullname = view.findViewById(R.id.tv_fullName_story)
        backGroundStory = view.findViewById(R.id.iv_background_story)
    }
}
