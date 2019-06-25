package org.wikiedufoundation.wikiedudashboard.data.network

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articles_edited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.coures_students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetailResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadResponse
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.ui.media_detail.data.MediaDetailsResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface WikiEduDashboardApi {

    @GET
    fun getCourseDetail(@Url url: String): Call<CourseDetailResponse>

    @GET
    fun getArticlesEdited(@Url url: String): Call<ArticlesEdited>

    @GET
    fun getStudentList(@Url url: String): Call<StudentListResponse>

    @GET
    fun getCourseUploads(@Url url: String): Call<CourseUploadResponse>

    @GET("dashboard.json")
    fun getDashboardDetail(@Header("Cookie") sessionIdAndToken: String): Call<MyDashboardResponse>

    @GET("explore.json")
    fun getExploreCourses(@Header("Cookie") sessionIdAndToken: String): Call<ExploreCoursesResponse>

    @GET("w/api.php?action=query&format=json&pageids=79744855&prop=globalusage|categories|imageinfo&iiprop=size|extmetadata|url&clshow=!hidden")
    fun getMediaDetailsFromCommons(@Header("Cookie") sessionIdAndToken: String): Call<MediaDetailsResponse>
}
