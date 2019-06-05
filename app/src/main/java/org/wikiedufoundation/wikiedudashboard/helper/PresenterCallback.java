package org.wikiedufoundation.wikiedudashboard.helper;

public interface PresenterCallback<T>{
    void onSuccess(Object t);
    void onFailure();
}
