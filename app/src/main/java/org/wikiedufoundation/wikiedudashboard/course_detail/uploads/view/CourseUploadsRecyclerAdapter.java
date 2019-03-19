package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.wikiedufoundation.wikiedudashboard.R;
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data.CourseUpload;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseUploadsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CourseUpload> courseUploads = new ArrayList<>();
    private CourseUpload courseUpload;

    CourseUploadsFragment courseUploadsFragment;

    CourseUploadsRecyclerAdapter(Context context, CourseUploadsFragment courseUploadsFragment) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.courseUploadsFragment = courseUploadsFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = layoutInflater.inflate(R.layout.item_rv_course_upload, parent, false);
        return new MyDashboardViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        courseUpload = courseUploads.get(position);
        final MyDashboardViewHolder myDashboardViewHolder = (MyDashboardViewHolder) holder;
        myDashboardViewHolder.tv_upload_title.setText(courseUpload.getFile_name());
        Glide.with(context).load(courseUpload.getThumbUrl()).into(myDashboardViewHolder.iv_course_upload);
        holder.itemView.setOnClickListener(view -> {
//            courseUploadsFragment.openCourseDetail(courseUploads.get(position).getSlug());
        });
    }


    void setData(List<CourseUpload> courseUploads) {
        this.courseUploads = courseUploads;
    }

    @Override
    public int getItemCount() {
        return courseUploads.size();
    }

    public class MyDashboardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_upload_title)
        TextView tv_upload_title;

        @BindView(R.id.iv_course_upload)
        ImageView iv_course_upload;

        public MyDashboardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}