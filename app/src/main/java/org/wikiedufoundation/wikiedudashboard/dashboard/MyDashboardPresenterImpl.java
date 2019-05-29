package org.wikiedufoundation.wikiedudashboard.dashboard;

import android.util.Log;

import org.wikiedufoundation.wikiedudashboard.dashboard.MyDashboardContract;
import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public class MyDashboardPresenterImpl implements MyDashboardContract.Presenter {
    private MyDashboardContract.View myDashboardView;
    private MyDashboardContract.Provider myDashboardProvider;

    public MyDashboardPresenterImpl(MyDashboardContract.View myDashboardView, MyDashboardContract.Provider myDashboardProvider) {
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
