package org.wikiedufoundation.wikiedudashboard.presenter_callback;

public interface PresenterCallback<T>{
    void onSuccess(T t);
    void onFailure();
}
