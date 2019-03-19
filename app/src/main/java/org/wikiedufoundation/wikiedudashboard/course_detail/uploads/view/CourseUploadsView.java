package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.view;

import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data.CourseUploadList;
import org.wikiedufoundation.wikiedudashboard.helper.Progressive;
import org.wikiedufoundation.wikiedudashboard.helper.Toaster;

public interface CourseUploadsView extends Progressive, Toaster {
    void setData(CourseUploadList courseUploadList);
}
