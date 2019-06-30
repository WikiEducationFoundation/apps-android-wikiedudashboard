package org.wikiedufoundation.wikiedudashboard.data.network

import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetailResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadResponse
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaDetailsResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
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
    fun getRecentActivity(@Url url: String): Call<RecentActivityResponse>

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
    fun getDashboardDetail(@Header("Cookie") sessionIdAndToken: String): Call<MyDashboardResponse>

    /**
     * This API is used to fetch list of active courses.
     ***/
    @GET("explore.json")
    fun getExploreCourses(@Header("Cookie") sessionIdAndToken: String): Call<ExploreCoursesResponse>

    /**
     * This API is used to fetch list of active campaigns.
     ***/
    @GET("campaigns.json")
    fun getExploreCampaigns(@Header("Cookie") sessionIdAndToken: String): Call<ExploreCampaignsResponse>

    /**
     * This API is used to fetch media details including category list, file uses, description,
     * license from Wikimedia Commons.
     ***/
    @GET("w/api.php?action=query&format=json&pageids=79744855&prop=globalusage|categories|imageinfo&iiprop=size|extmetadata|url&clshow=!hidden")
    fun getMediaDetailsFromCommons(@Header("Cookie") sessionIdAndToken: String): Call<MediaDetailsResponse>
}
