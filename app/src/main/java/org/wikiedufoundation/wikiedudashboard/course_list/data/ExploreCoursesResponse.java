package org.wikiedufoundation.wikiedudashboard.course_list.data;

import org.wikiedufoundation.wikiedudashboard.dashboard.data.CourseListData;
import org.wikiedufoundation.wikiedudashboard.dashboard.data.UserData;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ExploreCoursesResponse {
    private List<CourseListData> courses;
}
