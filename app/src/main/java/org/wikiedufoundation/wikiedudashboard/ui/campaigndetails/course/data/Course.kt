package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.data

import android.os.Parcel
import android.os.Parcelable
/**
 * This is course data class and implements parcelable.
 ***/
data class Course(
    val id: Int,
    val title: String,
    val username: String,
    val institution: String,
    val recent_edit: String,
    val words_added: String,
    val references_added: String,
    val views: String,
    val editor: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(username)
        parcel.writeString(institution)
        parcel.writeString(recent_edit)
        parcel.writeString(words_added)
        parcel.writeString(references_added)
        parcel.writeString(views)
        parcel.writeString(editor)
    }

    override fun describeContents(): Int {
        return 0
    }

    /**
     * This Parcelable companion object.
     ***/
    companion object CREATOR : Parcelable.Creator<Course> {
        override fun createFromParcel(parcel: Parcel): Course {
            return Course(parcel)
        }

        override fun newArray(size: Int): Array<Course?> {
            return arrayOfNulls(size)
        }
    }
}
