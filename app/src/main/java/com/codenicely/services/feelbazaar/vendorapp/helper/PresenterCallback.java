package com.codenicely.services.feelbazaar.vendorapp.helper;

public interface PresenterCallback<T>{
    void onSuccess(T t);
    void onFailure();
}
