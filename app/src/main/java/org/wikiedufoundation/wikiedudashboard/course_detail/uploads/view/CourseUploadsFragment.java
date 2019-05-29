package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.view;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.wikiedufoundation.wikiedudashboard.R;
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data.CourseUploadList;
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.presenter.CourseUploadsPresenterImpl;
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.provider.RetrofitCourseUploadsProvider;
import org.wikiedufoundation.wikiedudashboard.helper.SharedPrefs;
import org.wikiedufoundation.wikiedudashboard.helper.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link CourseUploadsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseUploadsFragment extends Fragment implements CourseUploadsView{
    private static final String ARG_PARAM1 = "param1";

    private String courseUrl;

    @BindView(R.id.tv_no_courses)
    TextView tv_no_courses;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.rv_order_list)
    RecyclerView recyclerView;

    private SharedPrefs sharedPrefs;
    private Context context;
    private CourseUploadsPresenterImpl courseUploadsPresenter;
    private CourseUploadsRecyclerAdapter courseUploadsRecyclerAdapter;

    public CourseUploadsFragment() {
        // Required empty public constructor
    }

    public static CourseUploadsFragment newInstance(String courseDetail) {
        CourseUploadsFragment fragment = new CourseUploadsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, courseDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseUrl = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore_course_list, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        sharedPrefs = new SharedPrefs(context);
        tv_no_courses.setText(sharedPrefs.getCookies());
        courseUploadsPresenter = new CourseUploadsPresenterImpl(this, new RetrofitCourseUploadsProvider());
        courseUploadsRecyclerAdapter = new CourseUploadsRecyclerAdapter(context, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(courseUploadsRecyclerAdapter);
        courseUploadsPresenter.requestCourseUploads(courseUrl);
        return view;
    }

    @Override
    public void setData(CourseUploadList courseUploadList) {
        Log.d("DashboardFragment: ", courseUploadList.toString());
        if (courseUploadList.getUploads().size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            courseUploadsRecyclerAdapter.setData(courseUploadList.getUploads());
            courseUploadsRecyclerAdapter.notifyDataSetChanged();
            tv_no_courses.setVisibility(View.GONE);
        }
        else {
            recyclerView.setVisibility(View.GONE);
            tv_no_courses.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showProgressBar(boolean show) {
        if (show){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(context, message);
    }
}
