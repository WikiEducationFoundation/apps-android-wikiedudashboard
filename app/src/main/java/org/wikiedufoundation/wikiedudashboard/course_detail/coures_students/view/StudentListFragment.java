package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.data.StudentListResponse;
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.presenter.StudentListPresenterImpl;
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.provider.RetrofitStudentListProvider;
import org.wikiedufoundation.wikiedudashboard.helper.SharedPrefs;
import org.wikiedufoundation.wikiedudashboard.helper.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StudentListFragment extends Fragment implements StudentListView {

    @BindView(R.id.rv_students_list)
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    StudentListPresenterImpl studentListPresenter;

    private String url;
    private Context context;
    private StudentListRecyclerAdapter studentListRecyclerAdapter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tv_no_students)
    TextView tv_no_students;

    SharedPrefs sharedPrefs;

    public StudentListFragment(){
        // Empty Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_explore_students,container,false);
        url=getArguments().getString("url",null);
        ButterKnife.bind(this,view);
        context=getContext();
        studentListPresenter=new StudentListPresenterImpl(this,new RetrofitStudentListProvider());
        layoutManager=new LinearLayoutManager(context);
        sharedPrefs = new SharedPrefs(context);
        tv_no_students.setText(sharedPrefs.getCookies());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        studentListRecyclerAdapter = new StudentListRecyclerAdapter(context);
        recyclerView.setAdapter(studentListRecyclerAdapter);
        studentListPresenter.requestStudentList(url);

        return view;
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

    @Override
    public void setData(StudentListResponse data) {
        if (data.getCourse().getUsers().size() > 0) {
            Log.d("StudentListFragment: ", data.toString());
            studentListRecyclerAdapter.setData(data.getCourse().getUsers());
            studentListRecyclerAdapter.notifyDataSetChanged();
        }
        else {
            recyclerView.setVisibility(View.GONE);
            tv_no_students.setVisibility(View.VISIBLE);
        }

    }
}
