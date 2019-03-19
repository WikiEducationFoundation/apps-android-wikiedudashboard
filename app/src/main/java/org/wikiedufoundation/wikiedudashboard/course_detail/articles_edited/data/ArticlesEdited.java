package org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticlesEdited implements Serializable {
    private  Course course;
}
