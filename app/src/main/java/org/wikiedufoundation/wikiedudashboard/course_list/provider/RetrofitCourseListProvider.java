package org.wikiedufoundation.wikiedudashboard.course_list.provider;

import org.wikiedufoundation.wikiedudashboard.course_list.data.ExploreCoursesResponse;
import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;
import org.wikiedufoundation.wikiedudashboard.helper.ProviderUtils;
import org.wikiedufoundation.wikiedudashboard.helper.WikiEduDashboardApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCourseListProvider implements CourseListProvider {

    private WikiEduDashboardApi wikiEduDashboardApi;

    public RetrofitCourseListProvider() {
        wikiEduDashboardApi = ProviderUtils.getApiObject();
    }

    @Override
    public void requestCourseList(String cookies, final PresenterCallback presenterCallback) {
        Call<ExploreCoursesResponse> courseDetailResponseCall =
                wikiEduDashboardApi.getExploreCourses(cookies);
        courseDetailResponseCall.enqueue(new Callback<ExploreCoursesResponse>() {
            @Override
            public void onResponse(Call<ExploreCoursesResponse> call, Response<ExploreCoursesResponse> response) {
                presenterCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ExploreCoursesResponse> call, Throwable t) {
                presenterCallback.onFailure();
            }
        });
    }
}
