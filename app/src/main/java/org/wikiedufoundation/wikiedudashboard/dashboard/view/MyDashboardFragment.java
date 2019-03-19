package org.wikiedufoundation.wikiedudashboard.dashboard.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.wikiedufoundation.wikiedudashboard.R;

import org.wikiedufoundation.wikiedudashboard.course_detail.common.view.CourseDetailActivity;
import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse;
import org.wikiedufoundation.wikiedudashboard.dashboard.presenter.MyDashboardPresenter;
import org.wikiedufoundation.wikiedudashboard.dashboard.presenter.MyDashboardPresenterImpl;
import org.wikiedufoundation.wikiedudashboard.dashboard.provider.RetrofitMyDashboardProvider;
import org.wikiedufoundation.wikiedudashboard.helper.SharedPrefs;
import org.wikiedufoundation.wikiedudashboard.helper.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link MyDashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyDashboardFragment extends Fragment implements MyDashboardView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @BindView(R.id.tv_no_courses)
    TextView tv_no_courses;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.rv_order_list)
    RecyclerView recyclerView;

    private SharedPrefs sharedPrefs;
    private Context context;
    private MyDashboardPresenter myDashboardPresenter;
    private MyDashboardRecyclerAdapter myDashboardRecyclerAdapter;
    public MyDashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyDashboardFragment newInstance(String param1, String param2) {
        MyDashboardFragment fragment = new MyDashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_dashboard, container, false);
        ButterKnife.bind(this, view);
        context = getContext();
        sharedPrefs = new SharedPrefs(context);
        tv_no_courses.setText(sharedPrefs.getCookies());
        myDashboardPresenter = new MyDashboardPresenterImpl(this, new RetrofitMyDashboardProvider());
        myDashboardRecyclerAdapter = new MyDashboardRecyclerAdapter(context, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myDashboardRecyclerAdapter);

        myDashboardPresenter.requestDashboard(sharedPrefs.getCookies());
        return view;
    }

    @Override
    public void setData(MyDashboardResponse data) {
        sharedPrefs.setUserName(data.getUser().getUsername());
        Log.d("DashboardFragment: ", data.toString());
        if (data.getCurrent_courses().size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            myDashboardRecyclerAdapter.setData(data.getCurrent_courses());
            myDashboardRecyclerAdapter.notifyDataSetChanged();
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

    public void openCourseDetail(String slug) {
        Intent i = new Intent(context, CourseDetailActivity.class);
        i.putExtra("url", slug);
        startActivity(i);
    }
}
