package org.wikiedufoundation.wikiedudashboard.ui.profile.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.util.Urls

/**
 * Declares the api as a private property in the constructor.
 * */
class ProfileRepositoryImpl(private val wikiEduDashboardApi: WikiEduDashboardApi) : ProfileRepository {

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function. This gets the user profile
     **/
    override suspend fun requestProfile(cookies: String, username: String) = withContext(Dispatchers.IO) {
        val request = wikiEduDashboardApi.getProfileResponse(cookies, username)
        request
    }

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.This gets the user detail profile
     **/
    override suspend fun requestProfileDetails(username: String) = withContext(Dispatchers.IO) {
        val request = wikiEduDashboardApi
                .getProfileDetailsResponse(Urls.PROFILE_DETAIL.format(username))
        val userProfileDetails = request.userProfile
        userProfileDetails
    }
}