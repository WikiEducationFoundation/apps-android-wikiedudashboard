package org.wikiedufoundation.wikiedudashboard.course_detail.presenter;

import android.util.Log;

import org.wikiedufoundation.wikiedudashboard.course_detail.data.CourseDetail;
import org.wikiedufoundation.wikiedudashboard.course_detail.data.CourseDetailResponse;
import org.wikiedufoundation.wikiedudashboard.course_detail.provider.CourseDetailProvider;
import org.wikiedufoundation.wikiedudashboard.course_detail.provider.RetrofitCourseDetailProvider;
import org.wikiedufoundation.wikiedudashboard.course_detail.view.CourseDetailActivity;
import org.wikiedufoundation.wikiedudashboard.course_detail.view.CourseDetailView;
import org.wikiedufoundation.wikiedudashboard.presenter_callback.PresenterCallback;

public class CourseDetailPresenterImpl implements CourseDetailPresenter {
    private CourseDetailView courseDetailView;
    private CourseDetailProvider courseDetailProvider;

    public CourseDetailPresenterImpl(CourseDetailView courseDetailView, CourseDetailProvider courseDetailProvider) {
        this.courseDetailView = courseDetailView;
        this.courseDetailProvider = courseDetailProvider;
    }

    @Override
    public void requestCourseDetail(String url) {
        courseDetailView.showProgressbar(true);
        courseDetailProvider.requestCourseDetail(url, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                courseDetailView.showProgressbar(false);
                Log.d("Ujjwal: ",o.toString());
                courseDetailView.setData(((CourseDetailResponse) o).getCourse());
            }

            @Override
            public void onFailure() {
                courseDetailView.showProgressbar(false);
                courseDetailView.showMessage("Unable to connect to server.");
            }
        });
    }
}
