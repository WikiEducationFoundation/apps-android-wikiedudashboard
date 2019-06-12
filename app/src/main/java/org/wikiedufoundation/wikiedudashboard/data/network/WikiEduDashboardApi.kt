package org.wikiedufoundation.wikiedudashboard.data.network

import org.wikiedufoundation.wikiedudashboard.ui.course_detail.articles_edited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.coures_students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.common.data.CourseDetailResponse
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.data.CourseUploadResponse
import org.wikiedufoundation.wikiedudashboard.ui.course_list.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse

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
}
