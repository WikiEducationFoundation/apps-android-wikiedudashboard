package org.wikiedufoundation.wikiedudashboard.course_detail.common.presenter;

import org.wikiedufoundation.wikiedudashboard.course_detail.common.data.CourseDetailResponse;
import org.wikiedufoundation.wikiedudashboard.course_detail.common.provider.CourseDetailProvider;
import org.wikiedufoundation.wikiedudashboard.course_detail.common.view.CourseDetailView;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public class CourseDetailPresenterImpl implements CourseDetailPresenter {
    private CourseDetailView courseDetailView;
    private CourseDetailProvider courseDetailProvider;

    public CourseDetailPresenterImpl(CourseDetailView courseDetailView, CourseDetailProvider courseDetailProvider) {
        this.courseDetailView = courseDetailView;
        this.courseDetailProvider = courseDetailProvider;
    }

    @Override
    public void requestCourseDetail(String url) {
        courseDetailView.showProgressBar(true);
        courseDetailProvider.requestCourseDetail(url, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                courseDetailView.showProgressBar(false);
                // TODO
                //                courseDetailView.setData(((CourseDetailResponse) o).);
            }

            @Override
            public void onFailure() {
                courseDetailView.showProgressBar(false);
                courseDetailView.showMessage("Unable to connect to server.");
            }
        });
    }
}
