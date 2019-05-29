package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.provider;

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public interface CourseUploadsProvider {
    void requestCourseUploads(String url, PresenterCallback presenterCallback);
}
