package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.view;
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.data.StudentListResponse;
import org.wikiedufoundation.wikiedudashboard.helper.Progressive;
import org.wikiedufoundation.wikiedudashboard.helper.Toaster;


public interface StudentListView extends Progressive, Toaster {
    void setData(StudentListResponse data);
}

