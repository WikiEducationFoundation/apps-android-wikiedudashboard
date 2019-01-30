package com.codenicely.services.feelbazaar.vendorapp.course_detail.provider;

import com.codenicely.services.feelbazaar.vendorapp.helper.PresenterCallback;

public interface CourseDetailProvider {
    void requestCourseDetail(String url, PresenterCallback presenterCallback);
}
