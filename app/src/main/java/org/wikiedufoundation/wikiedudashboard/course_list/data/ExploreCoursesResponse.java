package org.wikiedufoundation.wikiedudashboard.course_list.data;

import org.wikiedufoundation.wikiedudashboard.dashboard.data.CourseListData;
import org.wikiedufoundation.wikiedudashboard.dashboard.data.UserData;

import java.util.List;

public class ExploreCoursesResponse {
    private List<CourseListData> courses;

    public ExploreCoursesResponse(List<CourseListData> courses) {
        this.courses = courses;
    }

    public List<CourseListData> getCourses() {
        return courses;
    }
}
