package com.codenicely.services.feelbazaar.vendorapp.course_detail.provider;

import android.util.Log;

import com.codenicely.services.feelbazaar.vendorapp.course_detail.data.CourseDetailResponse;
import com.codenicely.services.feelbazaar.vendorapp.helper.PresenterCallback;
import com.codenicely.services.feelbazaar.vendorapp.helper.ProviderUtils;
import com.codenicely.services.feelbazaar.vendorapp.helper.WikiEduDashboardApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCourseDetailProvider implements CourseDetailProvider {

    private WikiEduDashboardApi wikiEduDashboardApi;
    public RetrofitCourseDetailProvider() {
        wikiEduDashboardApi = ProviderUtils.getApiObject();
    }

    @Override
    public void requestCourseDetail(String url, final PresenterCallback presenterCallback) {
        String sub_url = url + "/course.json";
        Call<CourseDetailResponse> courseDetailResponseCall = wikiEduDashboardApi.getCourseDetail(sub_url);
        courseDetailResponseCall.enqueue(new Callback<CourseDetailResponse>() {
            @Override
            public void onResponse(Call<CourseDetailResponse> call, Response<CourseDetailResponse> response) {
                Log.d("Success: ",response.body().getCourse()+"");
                presenterCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CourseDetailResponse> call, Throwable t) {
                presenterCallback.onFailure();
                t.printStackTrace();
                Log.d("Failure: ",t.getMessage()+"");
            }
        });
    }
}
