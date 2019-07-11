package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_rv_course_upload.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsFragment
import java.util.*

class CourseUploadsRecyclerAdapter(private val context: Context, internal var courseUploadsFragment: CourseUploadsFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var courseUploadList: CourseUploadList? = null
    private var courseUploads: List<CourseUpload> = ArrayList()
    private var courseUpload: CourseUpload? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view1 = LayoutInflater.from(context).inflate(R.layout.item_rv_course_upload, parent, false)
        return MyDashboardViewHolder(view1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        courseUpload = courseUploads[position]
        val myDashboardViewHolder = holder as MyDashboardViewHolder
        myDashboardViewHolder.tvUploadTitle.text = courseUpload!!.file_name
        Glide.with(context).load(courseUpload!!.thumbUrl).into(myDashboardViewHolder.ivCourseUpload)
        holder.itemView.setOnClickListener {
            courseUploadsFragment.openCourseDetail(courseUploadList, position)
        }
    }

    fun setData(courseUploads: CourseUploadList) {
        this.courseUploads = courseUploads.uploads
        this.courseUploadList = courseUploads
    }

    override fun getItemCount(): Int {
        return courseUploads.size
    }

    inner class MyDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUploadTitle: TextView = itemView.tv_upload_title
        val ivCourseUpload: ImageView = itemView.iv_course_upload
    }
}