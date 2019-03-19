package org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.data;

public class Articles {
    private int id;
    private String title;

    public Articles(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
