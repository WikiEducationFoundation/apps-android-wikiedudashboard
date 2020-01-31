package org.wikiedufoundation.wikiedudashboard.ui.profile.repository

import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetails
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetailsResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse

/**
*Declares MediaDetailsRepository interface
* */
interface ProfileRepository {

    /**
     *Creates a suspend function [requestProfile]
     * */
    suspend fun requestProfile(cookies : String, username : String) : ProfileResponse

    /**
     *Creates a suspend function [requestProfileDetails]
     * */
    suspend fun requestProfileDetails(username : String): ProfileDetails?
}