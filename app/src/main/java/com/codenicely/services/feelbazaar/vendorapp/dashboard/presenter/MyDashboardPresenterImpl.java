package com.codenicely.services.feelbazaar.vendorapp.dashboard.presenter;

import android.util.Log;

import com.codenicely.services.feelbazaar.vendorapp.dashboard.data.MyDashboardResponse;
import com.codenicely.services.feelbazaar.vendorapp.dashboard.provider.MyDashboardProvider;
import com.codenicely.services.feelbazaar.vendorapp.dashboard.view.MyDashboardView;
import com.codenicely.services.feelbazaar.vendorapp.helper.PresenterCallback;

public class MyDashboardPresenterImpl implements MyDashboardPresenter {
    private MyDashboardView myDashboardView;
    private MyDashboardProvider myDashboardProvider;

    public MyDashboardPresenterImpl(MyDashboardView myDashboardView, MyDashboardProvider myDashboardProvider) {
        this.myDashboardView = myDashboardView;
        this.myDashboardProvider = myDashboardProvider;
    }

    @Override
    public void requestDashboard(String cookies) {
        myDashboardView.showProgressBar(true);
        myDashboardProvider.requestCourseList(cookies, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                myDashboardView.showProgressBar(false);
                MyDashboardResponse myDashboardResponse = (MyDashboardResponse)o;
                Log.d("Presenter: ", myDashboardResponse.toString());
                myDashboardView.setData(myDashboardResponse);
            }

            @Override
            public void onFailure() {
                myDashboardView.showProgressBar(false);
                myDashboardView.showMessage("Unable to connect to server.");

            }
        });
    }
}
