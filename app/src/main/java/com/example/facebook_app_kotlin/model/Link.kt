package com.example.facebook_app_kotlin.model

import android.os.Parcel
import android.os.Parcelable

class Link(
    var image: String? = null,
    var title: String? = null,
    var description: String? = null,
    var link: String? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(link)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Link(image=$image, title=$title, description=$description, link=$link)"
    }

    companion object CREATOR : Parcelable.Creator<Link> {
        override fun createFromParcel(parcel: Parcel): Link {
            return Link(parcel)
        }

        override fun newArray(size: Int): Array<Link?> {
            return arrayOfNulls(size)
        }
    }
}