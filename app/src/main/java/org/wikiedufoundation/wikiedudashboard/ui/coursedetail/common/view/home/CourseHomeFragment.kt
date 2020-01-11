package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_course_home.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetail
import java.text.MessageFormat
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CourseHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseHomeFragment : Fragment() {

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
    ): View? = inflater.inflate(R.layout.fragment_course_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textViewCountArticlesCreated.text = courseDetail?.createdCount
        textViewArticlesEdited.text = courseDetail?.editedCount
        textViewCountTotalEdits.text = courseDetail?.editCount
        textViewCountStudentEditors.text = MessageFormat.format("{0}", courseDetail?.studentCount)
        textViewCountWordsAdded.text = courseDetail?.wordCount
        textViewArticleViews.text = courseDetail?.viewCount
        textViewCountCommonsUploads.text = MessageFormat.format("{0}", courseDetail?.uploadCount)
        textViewCourseTitleLabel.text = courseDetail?.title
        textViewCourseDescription.text = courseDetail?.description
        textViewCourseSchool.text = courseDetail?.school
        textViewCourseTerm.text = courseDetail?.term
        textViewCoursePassCode.text = courseDetail?.passCode
        textViewCourseExpectedStudents.text = MessageFormat.format("{0}", courseDetail?.expectedStudents)

        textViewCourseStart.text = readableDate(courseDetail?.start)
        textViewCourseEndDetail.text = readableDate(courseDetail?.end)
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

        /**
         * This method converts date to readable format
         * using the [SimpleDateFormat] class
         */
        fun readableDate(realDate: String?): CharSequence? {
            val pattern = "EEE d MMM  yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
            val date = inputFormat.parse(realDate)
            return simpleDateFormat.format(date)
        }
    }
}