package com.codenicely.services.feelbazaar.vendorapp.course_detail.data;

public class CourseDetailResponse {
    private CourseDetail course;

    public CourseDetailResponse(CourseDetail course) {
        this.course = course;
    }

    public CourseDetail getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "CourseDetailResponse{" +
                "course=" + course +
                '}';
    }
}
