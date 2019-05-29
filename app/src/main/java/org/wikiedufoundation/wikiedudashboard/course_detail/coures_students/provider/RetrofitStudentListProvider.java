package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.provider;

import android.util.Log;
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.data.StudentListResponse;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;
import org.wikiedufoundation.wikiedudashboard.helper.ProviderUtils;
import org.wikiedufoundation.wikiedudashboard.helper.WikiEduDashboardApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitStudentListProvider implements StudentListProvider {

    private WikiEduDashboardApi wikiEduDashboardApi;

    public RetrofitStudentListProvider(){
        wikiEduDashboardApi= ProviderUtils.getApiObject();
    }

    @Override
    public void requestStudentList(String url, PresenterCallback presenterCallback) {
        String sub_url = "courses/" + url+"/users.json";
        Call<StudentListResponse> studentListCall=wikiEduDashboardApi.getStudentList(sub_url);
        studentListCall.enqueue(new Callback<StudentListResponse>() {
            @Override
            public void onResponse(Call<StudentListResponse> call, Response<StudentListResponse> response) {
                presenterCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StudentListResponse> call, Throwable t) {
                presenterCallback.onFailure();
                t.printStackTrace();
                Log.d("Failure: ",t.getMessage()+"");
            }

        });
    }
}
