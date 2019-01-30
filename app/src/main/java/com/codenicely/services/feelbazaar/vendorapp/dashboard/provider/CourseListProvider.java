package com.codenicely.services.feelbazaar.vendorapp.dashboard.provider;

import com.codenicely.services.feelbazaar.vendorapp.helper.PresenterCallback;

public interface CourseListProvider {
    void requestCourseList(String cookies, PresenterCallback presenterCallback);
}
