package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_explore_course_list.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.adapters.ProfileCourseListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailActivity
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.CourseData
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [ProfileCourseListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCourseListFragment : Fragment(), ProfileCourseListRecyclerAdapter.ProfileCourseListClickListener {

    private var mParam1: String? = null
    private var coursesList: List<CourseData> = ArrayList()
    private lateinit var courseListRecyclerAdapter: ProfileCourseListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
        if (arguments != null) {
            coursesList = (arguments?.getSerializable(ARG_PARAM1) as? ProfileResponse)?.courses ?: emptyList()
=======
        arguments?.let {
            coursesList = (it.getSerializable(ARG_PARAM1) as ProfileResponse).courses ?: emptyList()
>>>>>>> master
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore_course_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

<<<<<<< HEAD
        val sharedPrefs = SharedPrefs(context)
        tvNoCourses.text = sharedPrefs.cookies
        courseListRecyclerAdapter = ProfileCourseListRecyclerAdapter(context!!, this)
        rvCourseList.layoutManager = LinearLayoutManager(context)
        rvCourseList.adapter = courseListRecyclerAdapter
=======
        val sharedPrefs: SharedPrefs? = context?.let { SharedPrefs(it) }
        tv_no_courses?.text = sharedPrefs?.cookies
        courseListRecyclerAdapter = ProfileCourseListRecyclerAdapter(this)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = courseListRecyclerAdapter
>>>>>>> master
        setData(coursesList)
        showProgressBar(false)
    }

    fun setData(courses: List<CourseData>) {
        Timber.d(courses.toString())
        if (courses.isNotEmpty()) {
<<<<<<< HEAD
            rvCourseList.visibility = View.VISIBLE
            courseListRecyclerAdapter.setData(courses)
            courseListRecyclerAdapter.notifyDataSetChanged()
            tvNoCourses.visibility = View.GONE
        } else {
            rvCourseList.visibility = View.GONE
            tvNoCourses.visibility = View.VISIBLE
=======
            recyclerView?.visibility = View.VISIBLE
            courseListRecyclerAdapter?.setData(courses)
            courseListRecyclerAdapter?.notifyDataSetChanged()
            tv_no_courses?.visibility = View.GONE
        } else {
            recyclerView?.visibility = View.GONE
            tv_no_courses?.visibility = View.VISIBLE
>>>>>>> master
        }
    }

    fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    fun showMessage(message: String) {
        context?.showToast(message)
    }

    override fun onCourseClicked(courseSlug: String) {
        val i = Intent(context, CourseDetailActivity::class.java)
        i.putExtra("url", courseSlug)
        i.putExtra("enrolled", false)
        startActivity(i)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: ProfileResponse): ProfileCourseListFragment {
            val fragment = ProfileCourseListFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}
