package com.codenicely.services.feelbazaar.vendorapp.course_detail.presenter;

import android.util.Log;

import com.codenicely.services.feelbazaar.vendorapp.course_detail.data.CourseDetailResponse;
import com.codenicely.services.feelbazaar.vendorapp.course_detail.provider.CourseDetailProvider;
import com.codenicely.services.feelbazaar.vendorapp.course_detail.view.CourseDetailView;
import com.codenicely.services.feelbazaar.vendorapp.helper.PresenterCallback;

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
