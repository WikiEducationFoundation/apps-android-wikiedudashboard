package com.codenicely.services.feelbazaar.vendorapp.course_detail.data;

import java.io.Serializable;

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

    public CourseDetail(int id, String title, String description, String start, String end, String school, String subject, String slug, String url, boolean submitted, int expected_students, String timeline_start, String timeline_end, String day_exceptions, String weekdays, boolean no_day_exceptions, String updated_at, String string_prefix, boolean use_start_and_end_times, String type, int character_sum, int upload_count, int uploads_in_use_count, int upload_usages_count, int cloned_status, String level, String training_library_slug, boolean timeline_enabled, boolean home_wiki_edits_enabled, boolean wiki_edits_enabled, boolean assignment_edits_enabled, boolean wiki_course_page_enabled, boolean enrollment_edits_enabled, boolean account_requests_enabled, String term, boolean legacy, boolean ended, boolean published, boolean closed, String enroll_url, String created_count, String edited_count, String edit_count, int student_count, int trained_count, String word_count, String view_count, String character_sum_human, String passcode, boolean canUploadSyllabus) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.school = school;
        this.subject = subject;
        this.slug = slug;
        this.url = url;
        this.submitted = submitted;
        this.expected_students = expected_students;
        this.timeline_start = timeline_start;
        this.timeline_end = timeline_end;
        this.day_exceptions = day_exceptions;
        this.weekdays = weekdays;
        this.no_day_exceptions = no_day_exceptions;
        this.updated_at = updated_at;
        this.string_prefix = string_prefix;
        this.use_start_and_end_times = use_start_and_end_times;
        this.type = type;
        this.character_sum = character_sum;
        this.upload_count = upload_count;
        this.uploads_in_use_count = uploads_in_use_count;
        this.upload_usages_count = upload_usages_count;
        this.cloned_status = cloned_status;
        this.level = level;
        this.training_library_slug = training_library_slug;
        this.timeline_enabled = timeline_enabled;
        this.home_wiki_edits_enabled = home_wiki_edits_enabled;
        this.wiki_edits_enabled = wiki_edits_enabled;
        this.assignment_edits_enabled = assignment_edits_enabled;
        this.wiki_course_page_enabled = wiki_course_page_enabled;
        this.enrollment_edits_enabled = enrollment_edits_enabled;
        this.account_requests_enabled = account_requests_enabled;
        this.term = term;
        this.legacy = legacy;
        this.ended = ended;
        this.published = published;
        this.closed = closed;
        this.enroll_url = enroll_url;
        this.created_count = created_count;
        this.edited_count = edited_count;
        this.edit_count = edit_count;
        this.student_count = student_count;
        this.trained_count = trained_count;
        this.word_count = word_count;
        this.view_count = view_count;
        this.character_sum_human = character_sum_human;
        this.passcode = passcode;
        this.canUploadSyllabus = canUploadSyllabus;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getSchool() {
        return school;
    }

    public String getSubject() {
        return subject;
    }

    public String getSlug() {
        return slug;
    }

    public String getUrl() {
        return url;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public int getExpected_students() {
        return expected_students;
    }

    public String getTimeline_start() {
        return timeline_start;
    }

    public String getTimeline_end() {
        return timeline_end;
    }

    public String getDay_exceptions() {
        return day_exceptions;
    }

    public String getWeekdays() {
        return weekdays;
    }

    public boolean isNo_day_exceptions() {
        return no_day_exceptions;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getString_prefix() {
        return string_prefix;
    }

    public boolean isUse_start_and_end_times() {
        return use_start_and_end_times;
    }

    public String getType() {
        return type;
    }

    public int getCharacter_sum() {
        return character_sum;
    }

    public int getUpload_count() {
        return upload_count;
    }

    public int getUploads_in_use_count() {
        return uploads_in_use_count;
    }

    public int getUpload_usages_count() {
        return upload_usages_count;
    }

    public int getCloned_status() {
        return cloned_status;
    }

    public String getLevel() {
        return level;
    }

    public String getTraining_library_slug() {
        return training_library_slug;
    }

    public boolean isTimeline_enabled() {
        return timeline_enabled;
    }

    public boolean isHome_wiki_edits_enabled() {
        return home_wiki_edits_enabled;
    }

    public boolean isWiki_edits_enabled() {
        return wiki_edits_enabled;
    }

    public boolean isAssignment_edits_enabled() {
        return assignment_edits_enabled;
    }

    public boolean isWiki_course_page_enabled() {
        return wiki_course_page_enabled;
    }

    public boolean isEnrollment_edits_enabled() {
        return enrollment_edits_enabled;
    }

    public boolean isAccount_requests_enabled() {
        return account_requests_enabled;
    }

    public String getTerm() {
        return term;
    }

    public boolean isLegacy() {
        return legacy;
    }

    public boolean isEnded() {
        return ended;
    }

    public boolean isPublished() {
        return published;
    }

    public boolean isClosed() {
        return closed;
    }

    public String getEnroll_url() {
        return enroll_url;
    }

    public String getCreated_count() {
        return created_count;
    }

    public String getEdited_count() {
        return edited_count;
    }

    public String getEdit_count() {
        return edit_count;
    }

    public int getStudent_count() {
        return student_count;
    }

    public int getTrained_count() {
        return trained_count;
    }

    public String getWord_count() {
        return word_count;
    }

    public String getView_count() {
        return view_count;
    }

    public String getCharacter_sum_human() {
        return character_sum_human;
    }

    public String getPasscode() {
        return passcode;
    }

    public boolean isCanUploadSyllabus() {
        return canUploadSyllabus;
    }

    @Override
    public String toString() {
        return "CourseDetailResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", school='" + school + '\'' +
                ", subject='" + subject + '\'' +
                ", slug='" + slug + '\'' +
                ", url='" + url + '\'' +
                ", submitted=" + submitted +
                ", expected_students=" + expected_students +
                ", timeline_start='" + timeline_start + '\'' +
                ", timeline_end='" + timeline_end + '\'' +
                ", day_exceptions='" + day_exceptions + '\'' +
                ", weekdays='" + weekdays + '\'' +
                ", no_day_exceptions=" + no_day_exceptions +
                ", updated_at='" + updated_at + '\'' +
                ", string_prefix='" + string_prefix + '\'' +
                ", use_start_and_end_times=" + use_start_and_end_times +
                ", type='" + type + '\'' +
                ", character_sum=" + character_sum +
                ", upload_count=" + upload_count +
                ", uploads_in_use_count=" + uploads_in_use_count +
                ", upload_usages_count=" + upload_usages_count +
                ", cloned_status=" + cloned_status +
                ", level='" + level + '\'' +
                ", training_library_slug='" + training_library_slug + '\'' +
                ", timeline_enabled=" + timeline_enabled +
                ", home_wiki_edits_enabled=" + home_wiki_edits_enabled +
                ", wiki_edits_enabled=" + wiki_edits_enabled +
                ", assignment_edits_enabled=" + assignment_edits_enabled +
                ", wiki_course_page_enabled=" + wiki_course_page_enabled +
                ", enrollment_edits_enabled=" + enrollment_edits_enabled +
                ", account_requests_enabled=" + account_requests_enabled +
                ", term='" + term + '\'' +
                ", legacy=" + legacy +
                ", ended=" + ended +
                ", published=" + published +
                ", closed=" + closed +
                ", enroll_url='" + enroll_url + '\'' +
                ", created_count='" + created_count + '\'' +
                ", edited_count='" + edited_count + '\'' +
                ", edit_count='" + edit_count + '\'' +
                ", student_count=" + student_count +
                ", trained_count=" + trained_count +
                ", word_count='" + word_count + '\'' +
                ", view_count='" + view_count + '\'' +
                ", character_sum_human='" + character_sum_human + '\'' +
                ", passcode='" + passcode + '\'' +
                ", canUploadSyllabus=" + canUploadSyllabus +
                '}';
    }
}
