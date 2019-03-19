package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.provider;

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public interface StudentListProvider {
    void requestStudentList(String url, PresenterCallback presenterCallback);
}
