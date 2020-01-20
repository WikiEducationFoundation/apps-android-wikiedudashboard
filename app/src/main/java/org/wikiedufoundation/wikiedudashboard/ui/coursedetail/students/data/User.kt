package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data

import com.google.gson.annotations.SerializedName

/**
 * [User] model class
 * @constructor primary constructor to initialize properties and variables related to an user
 * ***/
data class User(
    @SerializedName("id")
    val id: Long,
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("username")
    var userName: String,
    @SerializedName("characterSumDraft")
    val characterSumDraft: Long,
    @SerializedName("characterSumMs")
    val characterSumMs: Long,
    @SerializedName("characterSumUs")
    val characterSumUs: Long,
    @SerializedName("contentExpert")
    val contentExpert: Boolean,
    @SerializedName("contributionUrl")
    val contributionUrl: String,
    @SerializedName("sandboxUrl")
    val sandboxUrl: String,
    @SerializedName("totalUploads")
    val totalUploads: Long,
    @SerializedName("courseTrainingProgress")
    val courseTrainingProgress: String,
    @SerializedName("enrolledAt")
    val enrolledAt: String,
    @SerializedName("programManager")
    val programManager: Boolean,
    @SerializedName("recentRevisions")
    val recentRevisions: Long,
    @SerializedName("role")
    val role: Long,
    @SerializedName("roleDescription")
    val roleDescription: String
)
