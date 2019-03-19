package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.wikiedufoundation.wikiedudashboard.R;
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.data.User;

import java.util.ArrayList;
import java.util.List;

public class StudentListRecyclerAdapter extends RecyclerView.Adapter<StudentListRecyclerAdapter.MyAdapter>{
    private List<User> studentList = new ArrayList<>();
    private Context context;

    public StudentListRecyclerAdapter( Context context) {
        this.context=context;
    }


    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_rv_students,viewGroup,false);

        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListRecyclerAdapter.MyAdapter myAdapter, int i) {
    myAdapter.textView.setText(studentList.get(i).getUsername());
    }

    void setData(List<User> studentList){
        this.studentList=studentList;
    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder {
        TextView textView;
        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_students_name);
        }
    }
}