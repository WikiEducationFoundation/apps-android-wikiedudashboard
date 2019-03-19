package org.wikiedufoundation.wikiedudashboard.dashboard.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MyDashboardResponse {
    private UserData user;
    private List<CourseListData> current_courses;
}
