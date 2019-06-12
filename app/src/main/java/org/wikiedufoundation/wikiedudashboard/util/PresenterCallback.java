package org.wikiedufoundation.wikiedudashboard.util;

public interface PresenterCallback<T>{
    void onSuccess(Object t);
    void onFailure();
}
