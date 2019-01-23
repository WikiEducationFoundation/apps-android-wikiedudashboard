package org.wikiedufoundation.wikiedudashboard.course_detail.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.wikiedufoundation.wikiedudashboard.R;
import org.wikiedufoundation.wikiedudashboard.course_detail.data.CourseDetail;
import org.wikiedufoundation.wikiedudashboard.course_detail.data.CourseDetailResponse;
import org.wikiedufoundation.wikiedudashboard.course_detail.presenter.CourseDetailPresenter;
import org.wikiedufoundation.wikiedudashboard.course_detail.presenter.CourseDetailPresenterImpl;
import org.wikiedufoundation.wikiedudashboard.course_detail.provider.RetrofitCourseDetailProvider;

public class CourseDetailActivity extends AppCompatActivity implements CourseDetailView{

    private CourseDetailPresenter courseDetailPresenter;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        url = getIntent().getStringExtra("url");
        courseDetailPresenter = new CourseDetailPresenterImpl(this, new RetrofitCourseDetailProvider());
        courseDetailPresenter.requestCourseDetail(url);
    }

    @Override
    public void showProgressbar(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setData(CourseDetail data) {

    }
}
