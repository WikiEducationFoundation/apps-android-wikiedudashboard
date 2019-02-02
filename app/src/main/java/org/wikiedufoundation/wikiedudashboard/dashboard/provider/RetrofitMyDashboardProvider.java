package org.wikiedufoundation.wikiedudashboard.dashboard.provider;

import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;
import org.wikiedufoundation.wikiedudashboard.helper.ProviderUtils;
import org.wikiedufoundation.wikiedudashboard.helper.WikiEduDashboardApi;

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
                wikiEduDashboardApi.getDashboardDetail(cookies);
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
