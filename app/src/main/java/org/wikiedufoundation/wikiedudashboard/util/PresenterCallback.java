package org.wikiedufoundation.wikiedudashboard.util;

/**
 * Retrofit2 HTTP request result callbacks
 * ***/
public interface PresenterCallback<T>{
    void onSuccess(T t);
    void onFailure();
}
