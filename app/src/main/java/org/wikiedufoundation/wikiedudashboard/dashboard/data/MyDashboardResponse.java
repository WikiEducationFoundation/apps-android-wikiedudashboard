package org.wikiedufoundation.wikiedudashboard.dashboard.data;

import java.util.List;

public class MyDashboardResponse {
    private UserData user;
    private List<CourseListData> current_courses;

    public MyDashboardResponse(UserData user, List<CourseListData> current_courses) {
        this.user = user;
        this.current_courses = current_courses;
    }

    public UserData getUser() {
        return user;
    }

    public List<CourseListData> getCurrent_courses() {
        return current_courses;
    }

    @Override
    public String toString() {
        return "MyDashboardResponse{" +
                "user=" + user +
                ", current_courses=" + current_courses +
                '}';
    }
}
