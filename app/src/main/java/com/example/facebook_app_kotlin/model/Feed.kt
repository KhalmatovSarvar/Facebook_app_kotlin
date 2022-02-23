package com.example.facebook_app_kotlin.model

import com.example.facebook_app_kotlin.model.Story as Story

class Feed {
    var isHeader:Boolean = false
    var post:Post? = null
    var stories: ArrayList<Story> = ArrayList()
    var photos:PostAddPhotos? = null
    var link:Link? = null


    constructor(){
        this.isHeader = true
    }
    constructor(post:Post){
        this.post = post
        this.isHeader = false
    }
    constructor(stories:ArrayList<Story>){
        this.stories =  stories
        this.isHeader = false
    }
   constructor(photos:PostAddPhotos){
       this.photos = photos
       this.isHeader = false
   }
    constructor(link:Link){
        this.link = link
        this.isHeader =  false
    }
}

