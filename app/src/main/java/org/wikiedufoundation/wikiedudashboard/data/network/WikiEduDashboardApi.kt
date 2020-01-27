package org.wikiedufoundation.wikiedudashboard.data.network

import kotlinx.coroutines.Deferred
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetailResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadResponse
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetailsResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface WikiEduDashboardApi {

    /**
     * This API is used to fetch course details and statistics.
     ***/
    @GET
    fun getCourseDetail(@Url url: String): Call<CourseDetailResponse>

    /**
     * This API is used to fetch list of articles of a course.
     ***/
    @GET
    fun getArticlesEdited(@Url url: String): Call<ArticlesEdited>

    /**
     * This API is used to fetch list of articles edited in a course.
     ***/
    @GET
    suspend fun getRecentActivity(@Url url: String): RecentActivityResponse

    /**
     * This API is used to fetch list of students in a course.
     ***/
    @GET
    fun getStudentList(@Url url: String): Call<StudentListResponse>

    /**
     * This API is used to fetch list of uploads by the users in a course.
     ***/
    @GET
    fun getCourseUploads(@Url url: String): Call<CourseUploadResponse>

    /**
     * This API is used to fetch Dashboard details. Ex - My Courses - Active, Archived.
     ***/
    @GET("dashboard.json")
    fun getDashboardDetail(
        @Header("Cookie") sessionIdAndToken: String
    ): Deferred<MyDashboardResponse>

    /**
     * This API is used to fetch list of active courses.
     ***/
    @GET("explore.json")
    fun getExploreCourses(@Header("Cookie") sessionIdAndToken: String): Deferred<ExploreCoursesResponse>

    /**
     * This API is used to fetch list of active campaigns.
     ***/
    @GET("campaigns.json")
    fun getExploreCampaigns(@Header("Cookie") sessionIdAndToken: String): Deferred<ExploreCampaignsResponse>

    /**
     * This API is used to fetch profile stats.
     ***/
    @GET("user_stats.json")
    fun getProfileResponse(
        @Header("Cookie") sessionIdAndToken: String,
        @Query("username") username: String
    ): Call<ProfileResponse>

    /**
     * This API is used to fetch profile stats.
     ***/
    @GET
    fun getProfileDetailsResponse(
        @Url url: String
    ): Call<ProfileDetailsResponse>
}
