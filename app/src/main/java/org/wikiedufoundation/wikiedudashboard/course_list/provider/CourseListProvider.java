package org.wikiedufoundation.wikiedudashboard.course_list.provider;

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public interface CourseListProvider {
    void requestCourseList(String cookies, PresenterCallback presenterCallback);
}
