package org.wikiedufoundation.wikiedudashboard.ui.course_detail.common.view.timeline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.common.data.CourseDetail

import java.text.MessageFormat

import org.wikiedufoundation.wikiedudashboard.ui.course_detail.common.view.home.CourseHomeFragment

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
        if (arguments != null) {
            courseDetail = arguments!!.getSerializable(ARG_PARAM1) as CourseDetail
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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
        tvCourseSchool = view.findViewById(R.id.tv_course_school)
        tvCourseTerm = view.findViewById(R.id.tv_course_term)
        tvCoursePasscode = view.findViewById(R.id.tv_course_passcode)
        tvCourseExpectedStudents = view.findViewById(R.id.tv_course_expected_students)
        tvCountArticlesCreated = view.findViewById(R.id.tv_count_articles_created)
        tvCourseExpectedStudents = view.findViewById(R.id.tv_course_expected_students)
        tvCourseStart = view.findViewById(R.id.tv_course_start)
        tvCourseEnd = view.findViewById(R.id.tv_course_end)


        tvCountArticlesCreated!!.text = courseDetail!!.created_count
        tvCountArticlesEdited!!.text = courseDetail!!.edited_count
        tvCountTotalEdits!!.text = courseDetail!!.edit_count
        tvCountStudentEditors!!.text = MessageFormat.format("{0}", courseDetail!!.student_count)
        tvCountWordsAdded!!.text = courseDetail!!.word_count
        tvCountArticleViews!!.text = courseDetail!!.view_count
        tvCountCommonsUploads!!.text = MessageFormat.format("{0}", courseDetail!!.upload_count)
        tvCourseTitle!!.text = courseDetail!!.title
        tvCourseDescription!!.text = courseDetail!!.description
        tvCourseSchool!!.text = courseDetail!!.school
        tvCourseTerm!!.text = courseDetail!!.term
        tvCoursePasscode!!.text = courseDetail!!.passcode
        tvCourseExpectedStudents!!.text = MessageFormat.format("{0}", courseDetail!!.expected_students)
        tvCourseStart!!.text = courseDetail!!.start
        tvCourseEnd!!.text = courseDetail!!.end
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
