package com.codenicely.services.feelbazaar.vendorapp.dashboard.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenicely.services.feelbazaar.vendorapp.dashboard.data.CourseListData;

import org.wikiedufoundation.wikiedudashboard.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDashboardRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CourseListData> courses = new ArrayList<>();
    private CourseListData course;

    MyDashboardFragment myDashboardFragment;

    MyDashboardRecyclerAdapter(Context context, MyDashboardFragment myDashboardFragment) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.myDashboardFragment = myDashboardFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = layoutInflater.inflate(R.layout.item_rv_my_dashboard, parent, false);
        return new MyDashboardViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        course = courses.get(position);
        final MyDashboardViewHolder myDashboardViewHolder = (MyDashboardViewHolder) holder;
        myDashboardViewHolder.tv_course_title.setText(course.getTitle());
    }


    void setData(List<CourseListData> courses) {
        this.courses = courses;
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class MyDashboardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_course_title)
        TextView tv_course_title;


        public MyDashboardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}