package org.wikiedufoundation.wikiedudashboard.course_detail.common.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CourseDetail implements Serializable {
    private int id;
    private String title;
    private String description;
    private String start;
    private String end;
    private String school;
    private String subject;
    private String slug;
    private String url;
    private boolean submitted;
    private int expected_students;
    private String timeline_start;
    private String timeline_end;
    private String day_exceptions;
    private String weekdays;
    private boolean no_day_exceptions;
    private String updated_at;
    private String string_prefix;
    private boolean use_start_and_end_times;
    private String type;
    private int character_sum;
    private int upload_count;
    private int uploads_in_use_count;
    private int upload_usages_count;
    private int cloned_status;
    private String level;
    private String training_library_slug;
    private boolean timeline_enabled;
    private boolean home_wiki_edits_enabled;
    private boolean wiki_edits_enabled;
    private boolean assignment_edits_enabled;
    private boolean wiki_course_page_enabled;
    private boolean enrollment_edits_enabled;
    private boolean account_requests_enabled;
    private String term;
    private boolean legacy;
    private boolean ended;
    private boolean published;
    private boolean closed;
    private String enroll_url;
    private String created_count;
    private String edited_count;
    private String edit_count;
    private int student_count;
    private int trained_count;
    private String word_count;
    private String view_count;
    private String character_sum_human;
    private String passcode;
    private boolean canUploadSyllabus;

}
