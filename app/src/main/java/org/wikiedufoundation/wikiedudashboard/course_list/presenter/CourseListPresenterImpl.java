package org.wikiedufoundation.wikiedudashboard.course_list.presenter;

import android.util.Log;

import org.wikiedufoundation.wikiedudashboard.course_list.data.ExploreCoursesResponse;
import org.wikiedufoundation.wikiedudashboard.course_list.provider.CourseListProvider;
import org.wikiedufoundation.wikiedudashboard.course_list.view.CourseListView;
import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public class CourseListPresenterImpl implements CourseListPresenter {
    private CourseListView courseListView;
    private CourseListProvider courseListProvider;

    public CourseListPresenterImpl(CourseListView courseListView, CourseListProvider courseListProvider) {
        this.courseListView = courseListView;
        this.courseListProvider = courseListProvider;
    }

    @Override
    public void requestDashboard(String cookies) {
        courseListView.showProgressBar(true);
        courseListProvider.requestCourseList(cookies, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                courseListView.showProgressBar(false);
                ExploreCoursesResponse exploreCoursesResponse = (ExploreCoursesResponse) o;
                Log.d("Presenter: ", exploreCoursesResponse.toString());
                courseListView.setData(exploreCoursesResponse);
            }

            @Override
            public void onFailure() {
                courseListView.showProgressBar(false);
                courseListView.showMessage("Unable to connect to server.");

            }
        });
    }
}
