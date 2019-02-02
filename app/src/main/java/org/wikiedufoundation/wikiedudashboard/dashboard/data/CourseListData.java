package org.wikiedufoundation.wikiedudashboard.dashboard.data;

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

    public CourseListData(int id, String title, String created_at, String updated_at, String start, String end, String school, String term, String character_sum, String view_sum, String user_count, String article_count, String revision_count, String slug, String subject, int expected_students, String description, boolean submitted, String passcode, String timeline_start, String timeline_end, String day_exceptions, String weekdays, int new_article_count, boolean no_day_exceptions, int trained_count) {
        this.id = id;
        this.title = title;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.start = start;
        this.end = end;
        this.school = school;
        this.term = term;
        this.character_sum = character_sum;
        this.view_sum = view_sum;
        this.user_count = user_count;
        this.article_count = article_count;
        this.revision_count = revision_count;
        this.slug = slug;
        this.subject = subject;
        this.expected_students = expected_students;
        this.description = description;
        this.submitted = submitted;
        this.passcode = passcode;
        this.timeline_start = timeline_start;
        this.timeline_end = timeline_end;
        this.day_exceptions = day_exceptions;
        this.weekdays = weekdays;
        this.new_article_count = new_article_count;
        this.no_day_exceptions = no_day_exceptions;
        this.trained_count = trained_count;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
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

    public String getTerm() {
        return term;
    }

    public String getCharacter_sum() {
        return character_sum;
    }

    public String getView_sum() {
        return view_sum;
    }

    public String getUser_count() {
        return user_count;
    }

    public String getArticle_count() {
        return article_count;
    }

    public String getRevision_count() {
        return revision_count;
    }

    public String getSlug() {
        return slug;
    }

    public String getSubject() {
        return subject;
    }

    public int getExpected_students() {
        return expected_students;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public String getPasscode() {
        return passcode;
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

    public int getNew_article_count() {
        return new_article_count;
    }

    public boolean isNo_day_exceptions() {
        return no_day_exceptions;
    }

    public int getTrained_count() {
        return trained_count;
    }

    @Override
    public String toString() {
        return "CourseListData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", school='" + school + '\'' +
                ", term='" + term + '\'' +
                ", character_sum='" + character_sum + '\'' +
                ", view_sum='" + view_sum + '\'' +
                ", user_count='" + user_count + '\'' +
                ", article_count='" + article_count + '\'' +
                ", revision_count='" + revision_count + '\'' +
                ", slug='" + slug + '\'' +
                ", subject='" + subject + '\'' +
                ", expected_students=" + expected_students +
                ", description='" + description + '\'' +
                ", submitted=" + submitted +
                ", passcode='" + passcode + '\'' +
                ", timeline_start='" + timeline_start + '\'' +
                ", timeline_end='" + timeline_end + '\'' +
                ", day_exceptions='" + day_exceptions + '\'' +
                ", weekdays='" + weekdays + '\'' +
                ", new_article_count=" + new_article_count +
                ", no_day_exceptions=" + no_day_exceptions +
                ", trained_count=" + trained_count +
                '}';
    }
}
