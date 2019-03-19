package org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.provider;

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public interface ArticlesEditedProvider {
    void requestArticlesEdited(String url, PresenterCallback presenterCallback);
}
