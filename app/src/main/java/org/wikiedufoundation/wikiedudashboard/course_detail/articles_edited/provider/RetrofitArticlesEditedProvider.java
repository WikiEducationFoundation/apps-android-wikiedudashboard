package org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.provider;

import android.util.Log;

import org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.data.ArticlesEdited;

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;
import org.wikiedufoundation.wikiedudashboard.helper.ProviderUtils;
import org.wikiedufoundation.wikiedudashboard.helper.WikiEduDashboardApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitArticlesEditedProvider implements ArticlesEditedProvider {
    private WikiEduDashboardApi wikiEduDashboardApi;
    public RetrofitArticlesEditedProvider(){wikiEduDashboardApi = ProviderUtils.getApiObject(); }

    @Override
    public void requestArticlesEdited(String url,final PresenterCallback presenterCallback) {
        String sub_url = "courses/"+url+"/articles.json";
        Call<ArticlesEdited> articlesEditedResponseCall = wikiEduDashboardApi.getArticlesEdited(sub_url);
        articlesEditedResponseCall.enqueue(new Callback<ArticlesEdited>() {
            @Override
            public void onResponse(Call<ArticlesEdited> call, Response<ArticlesEdited> response) {
                Log.d("Success: ",response.body().getCourse()+"");
                presenterCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArticlesEdited> call, Throwable t) {
                presenterCallback.onFailure();
                t.printStackTrace();
                Log.d("Failure: ",t.getMessage()+"");
            }
        });
    }
}
