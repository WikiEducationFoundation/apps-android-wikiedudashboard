package org.wikiedufoundation.wikiedudashboard.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.view.CourseUploadsFragment

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.item_rv_course_upload.view.*

class CourseUploadsRecyclerAdapter(private val context: Context, internal var courseUploadsFragment: CourseUploadsFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var courseUploads: List<CourseUpload> = ArrayList()
    private var courseUpload: CourseUpload? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view1 = LayoutInflater.from(context).inflate(R.layout.item_rv_course_upload, parent, false)
        return MyDashboardViewHolder(view1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        courseUpload = courseUploads[position]
        val myDashboardViewHolder = holder as MyDashboardViewHolder
        myDashboardViewHolder.tv_upload_title.text = courseUpload!!.file_name
        Glide.with(context).load(courseUpload!!.thumbUrl).into(myDashboardViewHolder.iv_course_upload!!)
        holder.itemView.setOnClickListener {
            //            courseUploadsFragment.openCourseDetail(courseUploads.get(position).getSlug());
        }
    }


    fun setData(courseUploads: List<CourseUpload>) {
        this.courseUploads = courseUploads
    }

    override fun getItemCount(): Int {
        return courseUploads.size
    }

    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_upload_title: TextView = itemView.tv_upload_title
        val iv_course_upload: ImageView = itemView.iv_course_upload
    }
}