package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_course_home.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetail
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.home.CourseHomeFragment
import java.text.MessageFormat

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CourseTimelineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseTimelineFragment : Fragment() {

    private var courseDetail: CourseDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseDetail = it.getSerializable(ARG_PARAM1) as CourseDetail
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_course_home, container, false)

        textViewCountArticlesCreated.text = courseDetail?.createdCount
        textViewCountArticlesEdited.text = courseDetail?.editedCount
        textViewCountTotalEdits.text = courseDetail?.editCount
        textViewCountStudentEditors.text = MessageFormat.format("{0}", courseDetail?.studentCount)
        textViewCountWordsAdded.text = courseDetail?.wordCount
        textViewArticleViews.text = courseDetail?.viewCount
        textViewCountCommonsUploads.text = MessageFormat.format("{0}", courseDetail?.uploadCount)
        textViewCourseTitle.text = courseDetail?.title
        textViewCourseDescription.text = courseDetail?.description
        textViewCourseSchool.text = courseDetail?.school
        textViewCourseTerm.text = courseDetail?.term
        textViewCoursePassCode.text = courseDetail?.passCode
        textViewCourseExpectedStudents.text = MessageFormat.format("{0}", courseDetail?.expectedStudents)
        textViewCourseStart.text = courseDetail?.start
        textViewCourseEndDetail.text = courseDetail?.end

        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"

        fun newInstance(courseDetail: CourseDetail): CourseHomeFragment {
            val fragment = CourseHomeFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM1, courseDetail)
            fragment.arguments = args
            return fragment
        }
    }
}
