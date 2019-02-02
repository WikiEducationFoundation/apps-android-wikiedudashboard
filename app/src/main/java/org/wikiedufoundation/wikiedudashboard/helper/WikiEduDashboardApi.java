package org.wikiedufoundation.wikiedudashboard.helper;

import org.wikiedufoundation.wikiedudashboard.course_detail.data.CourseDetailResponse;
import org.wikiedufoundation.wikiedudashboard.course_list.data.ExploreCoursesResponse;
import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface WikiEduDashboardApi {

    @GET
    Call<CourseDetailResponse> getCourseDetail(@Url String url);

    @GET("dashboard.json")
    Call<MyDashboardResponse> getDashboardDetail(@Header("Cookie") String sessionIdAndToken);

    @GET("explore.json")
    Call<ExploreCoursesResponse> getExploreCourses(@Header("Cookie") String sessionIdAndToken);
}
