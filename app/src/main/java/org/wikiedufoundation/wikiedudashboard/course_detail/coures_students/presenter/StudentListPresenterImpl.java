package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.presenter;


import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.data.StudentListResponse;
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.provider.StudentListProvider;
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.view.StudentListView;

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;


public class StudentListPresenterImpl implements StudentListPresenter {
    private StudentListView studentListView;
    private StudentListProvider studentListProvider;

    public StudentListPresenterImpl(StudentListView studentListView,StudentListProvider studentListProvider) {
        this.studentListProvider=studentListProvider;
        this.studentListView=studentListView;
    }

    @Override
    public void requestStudentList(String url) {
        studentListView.showProgressBar(true);
        studentListProvider.requestStudentList(url, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                studentListView.showProgressBar(false);
                StudentListResponse studentListsResponse = (StudentListResponse) o;
                studentListView.setData(studentListsResponse);
            }

            @Override
            public void onFailure() {
                studentListView.showProgressBar(false);
                studentListView.showMessage("Unable to connect to server.");

            }
        });
    }




}
