package org.wikiedufoundation.wikiedudashboard.ui.dashboard.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_my_dashboard.progressBar
import kotlinx.android.synthetic.main.fragment_my_dashboard.recyclerCourseList
import kotlinx.android.synthetic.main.fragment_my_dashboard.textViewNoCourses
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.adapters.MyDashboardRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailActivity
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.viewmodel.DashboardViewModel
import org.wikiedufoundation.wikiedudashboard.util.filterOrEmptyList
import timber.log.Timber
import java.util.Locale
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [RecentActivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyDashboardFragment : Fragment() {
    private val sharedPrefs: SharedPrefs by inject()
    private val dashboardViewModel by viewModel<DashboardViewModel> { parametersOf(sharedPrefs.cookies) }
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var coursesList: List<CourseListData>? = ArrayList()

    private lateinit var myDashboardRecyclerAdapter: MyDashboardRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM1)
            mParam2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_my_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myDashboardRecyclerAdapter = MyDashboardRecyclerAdapter(R.layout.item_rv_my_dashboard) {
            openCourseDetail(it)
        }
        initializeRecyclerView()
        setData()
        showProgressBar()
        showMessage()
    }

    private fun initializeRecyclerView() {
        recyclerCourseList?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = myDashboardRecyclerAdapter
        }
    }

    /**
     *   This sets the data to be displayed on the recyclerview based on available data
     */
    fun setData() {
        dashboardViewModel.courseList.observe(this, androidx.lifecycle.Observer {
            Timber.d(it.toString())

            if (it.isNotEmpty()) {
                recyclerCourseList?.visibility = View.VISIBLE
                myDashboardRecyclerAdapter.setData(it)
                textViewNoCourses?.visibility = View.GONE
            } else {
                recyclerCourseList?.visibility = View.GONE
                textViewNoCourses?.visibility = View.VISIBLE
            }
        })
    }

    /**
     *   This shows the progressbar
     */
    fun showProgressBar() {
        dashboardViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    /**
     *   This shows the message
     */
    fun showMessage() {
        dashboardViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it?.showMsg
            view?.let { it1 ->
                if (message != null) {
                    Snackbar.make(it1, message, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    /**
     * Use [openCourseDetail] to put url slug and the boolean value of enrolled
     * Send the data through Bundle then start the [CourseDetailActivity]
     *
     * @param slug url slug
     * ***/
    fun openCourseDetail(slug: String) {
        val i = Intent(context, CourseDetailActivity::class.java)
        i.putExtra("url", slug)
        i.putExtra("enrolled", true)
        startActivity(i)
    }

    /**
     * Use [updateSearchQuery] to search course
     *
     * @param query query statement
     * ***/
    fun updateSearchQuery(query: String) {
        Timber.d(query)

        val courseFilterQuery = dashboardViewModel.courseList.value.filterOrEmptyList {
            it.title.toLowerCase(Locale.getDefault()).contains(query.toLowerCase(Locale.getDefault()))
        }
        myDashboardRecyclerAdapter.setData(courseFilterQuery)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String) = MyDashboardFragment().apply {
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            this.arguments = args
        }
    }
}