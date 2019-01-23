package org.wikiedufoundation.wikiedudashboard.course_detail.provider;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.wikiedufoundation.wikiedudashboard.api.WikiEduDashboardApi;
import org.wikiedufoundation.wikiedudashboard.course_detail.data.CourseDetailResponse;
import org.wikiedufoundation.wikiedudashboard.helper.Urls;
import org.wikiedufoundation.wikiedudashboard.presenter_callback.PresenterCallback;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCourseDetailProvider implements CourseDetailProvider {

    private WikiEduDashboardApi wikiEduDashboardApi;
    private Retrofit retrofit;
    public RetrofitCourseDetailProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        wikiEduDashboardApi = retrofit.create(WikiEduDashboardApi.class);
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
