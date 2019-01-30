package com.codenicely.services.feelbazaar.vendorapp.dashboard.data;

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
}
