package org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.presenter;

import android.util.Log;

import org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.data.ArticlesEdited;
import org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.provider.ArticlesEditedProvider;
import org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.view.ArticlesEditedView;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public class ArticlesEditedPresenterImpl implements ArticlesEditedPresenter {
    private ArticlesEditedProvider articlesEditedProvider;
    private ArticlesEditedView articlesEditedView;

    public ArticlesEditedPresenterImpl(ArticlesEditedProvider articlesEditedProvider, ArticlesEditedView articlesEditedView) {
        this.articlesEditedProvider = articlesEditedProvider;
        this.articlesEditedView = articlesEditedView;
    }

    @Override
    public void requestArticlesEdited(String url) {
        articlesEditedView.showProgressBar(true);
        articlesEditedProvider.requestArticlesEdited(url, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                articlesEditedView.showProgressBar(false);
                ArticlesEdited articlesEditedResponse =
                        (ArticlesEdited) o;
                Log.d("Presenter: ", articlesEditedResponse.toString());
                articlesEditedView.setData(articlesEditedResponse);
            }

            @Override
            public void onFailure() {
               articlesEditedView.showProgressBar(false);
               articlesEditedView.showMessage("unable to connect to server.");
            }
        });
    }
}
