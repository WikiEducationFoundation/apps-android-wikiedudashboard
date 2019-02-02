package org.wikiedufoundation.wikiedudashboard.dashboard.provider;

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public interface MyDashboardProvider {
    void requestCourseList(String cookies, PresenterCallback presenterCallback);
}
