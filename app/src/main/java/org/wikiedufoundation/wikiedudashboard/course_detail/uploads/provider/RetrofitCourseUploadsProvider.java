package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.provider;

import android.util.Log;

import org.wikiedufoundation.wikiedudashboard.course_detail.common.data.CourseDetailResponse;
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data.CourseUploadResponse;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;
import org.wikiedufoundation.wikiedudashboard.helper.ProviderUtils;
import org.wikiedufoundation.wikiedudashboard.helper.WikiEduDashboardApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCourseUploadsProvider implements CourseUploadsProvider {

    private WikiEduDashboardApi wikiEduDashboardApi;
    public RetrofitCourseUploadsProvider() {
        wikiEduDashboardApi = ProviderUtils.getApiObject();
    }

    @Override
    public void requestCourseUploads(String url, final PresenterCallback presenterCallback) {
        String sub_url = "courses/" + url + "/uploads.json";
        Call<CourseUploadResponse> courseDetailResponseCall = wikiEduDashboardApi.getCourseUploads(sub_url);
        courseDetailResponseCall.enqueue(new Callback<CourseUploadResponse>() {
            @Override
            public void onResponse(Call<CourseUploadResponse> call, Response<CourseUploadResponse> response) {
                Log.d("Success: ",response.body().getCourse()+"");
                presenterCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CourseUploadResponse> call, Throwable t) {
                presenterCallback.onFailure();
                t.printStackTrace();
                Log.d("Failure: ",t.getMessage()+"");
            }
        });
    }
}
