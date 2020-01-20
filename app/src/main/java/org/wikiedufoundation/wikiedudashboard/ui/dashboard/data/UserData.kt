package org.wikiedufoundation.wikiedudashboard.ui.dashboard.data

import com.google.gson.annotations.SerializedName

/**
 * [UserData] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
data class UserData(
    @SerializedName("username")
    val userName: String
)
