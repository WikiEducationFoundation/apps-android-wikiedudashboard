package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.data

import android.os.Parcel
import android.os.Parcelable
/**
 * Article data class.
 ***/
data class Articles(
    val id: Int,
    val title: String,
    val characters_added: String,
    val references_added: String,
    val views: String,
    val courses: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(characters_added)
        parcel.writeString(references_added)
        parcel.writeString(views)
        parcel.writeString(courses)
    }

    override fun describeContents(): Int {
        return 0
    }

    /**
     * Parcelable companion object
     ***/
    companion object CREATOR : Parcelable.Creator<Articles> {
        override fun createFromParcel(parcel: Parcel): Articles {
            return Articles(parcel)
        }

        override fun newArray(size: Int): Array<Articles?> {
            return arrayOfNulls(size)
        }
    }
}
