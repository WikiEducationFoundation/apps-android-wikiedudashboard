package org.wikiedufoundation.wikiedudashboard.course_detail.view;

import org.wikiedufoundation.wikiedudashboard.course_detail.data.CourseDetail;

public interface CourseDetailView {
    void showProgressbar(boolean show);
    void showMessage(String message);
    void setData(CourseDetail data);
}
