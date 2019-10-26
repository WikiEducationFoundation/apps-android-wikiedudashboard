package org.wikiedufoundation.wikiedudashboard.ui.profile.data

import com.google.gson.annotations.SerializedName

/**
 * [ProfileDetailsResponse] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class ProfileDetailsResponse (
        val userProfile: ProfileDetails?
)

/**
 * [ProfileDetails] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
class ProfileDetails (
        @SerializedName("username")
        val username: String,
        @SerializedName("profile_image")
        val profileImage: String,
        @SerializedName("bio")
        val bio: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("location")
        val location: String,
        @SerializedName("institution")
        val institution: String
)