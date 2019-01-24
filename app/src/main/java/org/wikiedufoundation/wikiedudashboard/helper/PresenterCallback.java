package org.wikiedufoundation.wikiedudashboard.helper;

public interface PresenterCallback<T>{
    void onSuccess(T t);
    void onFailure();
}
