package com.codenicely.services.feelbazaar.vendorapp.dashboard.provider;

import android.util.Log;

import com.codenicely.services.feelbazaar.vendorapp.dashboard.data.MyDashboardResponse;
import com.codenicely.services.feelbazaar.vendorapp.helper.PresenterCallback;
import com.codenicely.services.feelbazaar.vendorapp.helper.ProviderUtils;
import com.codenicely.services.feelbazaar.vendorapp.helper.WikiEduDashboardApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitMyDashboardProvider implements MyDashboardProvider {

    private WikiEduDashboardApi wikiEduDashboardApi;

    public RetrofitMyDashboardProvider() {
        wikiEduDashboardApi = ProviderUtils.getApiObject();
    }

    @Override
    public void requestCourseList(String cookies, final PresenterCallback presenterCallback) {
        Call<MyDashboardResponse> courseDetailResponseCall =
                wikiEduDashboardApi.getDashboardDetail("dashboard.json",cookies );
        courseDetailResponseCall.enqueue(new Callback<MyDashboardResponse>() {
            @Override
            public void onResponse(Call<MyDashboardResponse> call, Response<MyDashboardResponse> response) {
                presenterCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MyDashboardResponse> call, Throwable t) {
                presenterCallback.onFailure();
            }
        });
    }
}
