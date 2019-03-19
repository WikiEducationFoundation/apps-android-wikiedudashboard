package org.wikiedufoundation.wikiedudashboard.course_detail.common.provider;

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public interface CourseDetailProvider {
    void requestCourseDetail(String url, PresenterCallback presenterCallback);
}
