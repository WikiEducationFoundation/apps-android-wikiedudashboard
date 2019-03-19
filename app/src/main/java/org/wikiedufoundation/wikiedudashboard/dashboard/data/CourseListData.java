package org.wikiedufoundation.wikiedudashboard.dashboard.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CourseListData {
    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private String start;
    private String end;
    private String school;
    private String term;
    private String character_sum;
    private String view_sum;
    private String user_count;
    private String article_count;
    private String revision_count;
    private String slug;
    private String subject;
    private int expected_students;
    private String description;
    private boolean submitted;
    private String passcode;
    private String timeline_start;
    private String timeline_end;
    private String day_exceptions;
    private String weekdays;
    private int new_article_count;
    private boolean no_day_exceptions;
    private int trained_count;
}
