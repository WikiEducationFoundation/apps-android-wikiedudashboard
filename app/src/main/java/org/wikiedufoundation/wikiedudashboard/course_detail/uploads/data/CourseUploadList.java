package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CourseUploadList {
    List<CourseUpload> uploads;
}
