package org.wikiedufoundation.wikiedudashboard.util;

/**
 * Retrofit2 HTTP request result callbacks
 * ***/
public interface PresenterCallback<T>{
    void onSuccess(Object t);
    void onFailure();
}
