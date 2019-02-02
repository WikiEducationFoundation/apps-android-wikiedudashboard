package org.wikiedufoundation.wikiedudashboard.dashboard.presenter;

import android.util.Log;

import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse;
import org.wikiedufoundation.wikiedudashboard.dashboard.provider.MyDashboardProvider;
import org.wikiedufoundation.wikiedudashboard.dashboard.view.MyDashboardView;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

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
