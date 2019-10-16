package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
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

    private var tvCountArticlesCreated: TextView? = null
    private var tvCountArticlesEdited: TextView? = null
    private var tvCountTotalEdits: TextView? = null
    private var tvCountStudentEditors: TextView? = null
    private var tvCountWordsAdded: TextView? = null
    private var tvCountArticleViews: TextView? = null
    private var tvCountCommonsUploads: TextView? = null
    private var tvCourseTitle: TextView? = null
    private var tvCourseDescription: TextView? = null
    private var tvCourseSchool: TextView? = null
    private var tvCourseTerm: TextView? = null
    private var tvCoursePasscode: TextView? = null
    private var tvCourseExpectedStudents: TextView? = null
    private var tvCourseStart: TextView? = null
    private var tvCourseEnd: TextView? = null

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
        tvCountArticlesCreated = view.findViewById(R.id.tv_count_articles_created)
        tvCountArticlesEdited = view.findViewById(R.id.tv_count_articles_edited)
        tvCountTotalEdits = view.findViewById(R.id.tv_count_total_edits)
        tvCountStudentEditors = view.findViewById(R.id.tv_count_student_editors)
        tvCountWordsAdded = view.findViewById(R.id.tv_count_words_added)
        tvCountArticleViews = view.findViewById(R.id.tv_count_article_views)
        tvCountCommonsUploads = view.findViewById(R.id.tv_count_commons_uploads)
        tvCourseTitle = view.findViewById(R.id.tv_course_title)
        tvCourseDescription = view.findViewById(R.id.tv_course_description)
        tvCourseSchool = view.findViewById(R.id.tv_course_school_detail)
        tvCourseTerm = view.findViewById(R.id.tv_course_term_detail)
        tvCoursePasscode = view.findViewById(R.id.tv_course_passcode_detail)
        tvCourseExpectedStudents = view.findViewById(R.id.tv_course_expected_students_detail)
        tvCountArticlesCreated = view.findViewById(R.id.tv_count_articles_created)
        tvCourseExpectedStudents = view.findViewById(R.id.tv_course_expected_students_detail)
        tvCourseStart = view.findViewById(R.id.tv_course_start_detail)
        tvCourseEnd = view.findViewById(R.id.tv_course_end_detail)

        tvCountArticlesCreated?.text = courseDetail?.createdCount
        tvCountArticlesEdited?.text = courseDetail?.editedCount
        tvCountTotalEdits?.text = courseDetail?.editCount
        tvCountStudentEditors?.text = MessageFormat.format("{0}", courseDetail?.studentCount)
        tvCountWordsAdded?.text = courseDetail?.wordCount
        tvCountArticleViews?.text = courseDetail?.viewCount
        tvCountCommonsUploads?.text = MessageFormat.format("{0}", courseDetail?.uploadCount)
        tvCourseTitle?.text = courseDetail?.title
        tvCourseDescription?.text = courseDetail?.description
        tvCourseSchool?.text = courseDetail?.school
        tvCourseTerm?.text = courseDetail?.term
        tvCoursePasscode?.text = courseDetail?.passCode
        tvCourseExpectedStudents?.text = MessageFormat.format("{0}", courseDetail?.expectedStudents)
        tvCourseStart?.text = courseDetail?.start
        tvCourseEnd?.text = courseDetail?.end
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
