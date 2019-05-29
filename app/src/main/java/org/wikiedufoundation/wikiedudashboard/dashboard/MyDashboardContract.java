package org.wikiedufoundation.wikiedudashboard.dashboard;

import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;
import org.wikiedufoundation.wikiedudashboard.helper.Progressive;
import org.wikiedufoundation.wikiedudashboard.helper.Toaster;

public interface MyDashboardContract {

    interface View extends Progressive, Toaster {
        void setData(MyDashboardResponse data);
    }

    interface Presenter {
        void requestDashboard(String cookies);
    }

    interface Provider {
        void requestCourseList(String cookies, PresenterCallback presenterCallback);
    }
}
