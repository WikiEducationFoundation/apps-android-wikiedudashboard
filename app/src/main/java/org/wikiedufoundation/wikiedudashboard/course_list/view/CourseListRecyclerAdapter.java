package org.wikiedufoundation.wikiedudashboard.course_list.view;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.wikiedufoundation.wikiedudashboard.R;
import org.wikiedufoundation.wikiedudashboard.dashboard.data.CourseListData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CourseListData> courses = new ArrayList<>();
    private CourseListData course;

    CourseListFragment courseListFragment;

    CourseListRecyclerAdapter(Context context, CourseListFragment courseListFragment) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.courseListFragment = courseListFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = layoutInflater.inflate(R.layout.item_rv_explore_courses, parent, false);
        return new MyDashboardViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        course = courses.get(position);
        final MyDashboardViewHolder myDashboardViewHolder = (MyDashboardViewHolder) holder;
        myDashboardViewHolder.tv_course_title.setText(course.getTitle());
        holder.itemView.setOnClickListener(view -> {
            courseListFragment.openCourseDetail(courses.get(position).getSlug());
        });
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