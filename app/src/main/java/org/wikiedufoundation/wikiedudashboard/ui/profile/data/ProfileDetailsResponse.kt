package org.wikiedufoundation.wikiedudashboard.ui.profile.data

class ProfileDetailsResponse (
    val user_profile: ProfileDetails
)

class ProfileDetails (
    val username: String,
    val profile_image: String,
    val bio: String,
    val email: String,
    val location: String,
    val institution: String
)