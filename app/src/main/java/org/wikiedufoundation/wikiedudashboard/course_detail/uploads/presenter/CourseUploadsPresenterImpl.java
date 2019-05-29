package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.presenter;


import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data.CourseUploadResponse;
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.provider.CourseUploadsProvider;
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.view.CourseUploadsView;
import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback;

public class CourseUploadsPresenterImpl implements CourseUploadsPresenter {
    private CourseUploadsView courseUploadsView;
    private CourseUploadsProvider courseUploadsProvider;

    public CourseUploadsPresenterImpl(CourseUploadsView courseUploadsView, CourseUploadsProvider courseUploadsProvider) {
        this.courseUploadsView = courseUploadsView;
        this.courseUploadsProvider = courseUploadsProvider;
    }

    @Override
    public void requestCourseUploads(String url) {
        courseUploadsView.showProgressBar(true);
        courseUploadsProvider.requestCourseUploads(url, new PresenterCallback() {
            @Override
            public void onSuccess(Object o) {
                courseUploadsView.showProgressBar(false);
                courseUploadsView.setData(((CourseUploadResponse) o).getCourse());
            }

            @Override
            public void onFailure() {
                courseUploadsView.showProgressBar(false);
                courseUploadsView.showMessage("Unable to connect to server.");
            }
        });
    }
}
