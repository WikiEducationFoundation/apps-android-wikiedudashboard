package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.databinding.FragmentCourseDetailBinding
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.data.Course

/**
 * A simple [Fragment] subclass.
 */
class CourseDetailFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCourseDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_course_detail,
                container, false)

        arguments?.let { bundle ->
            bundle.getParcelable<Course>("course")
                    ?.let {
                        binding.textViewTitle.text = it.title
                        binding.textUsername.text = it.username
                        binding.textViewInstitutionName.text = it.institution
                        binding.textViewCountRecentEdit.text = it.recent_edit
                        binding.textViewCountWordsAdded.text = it.words_added
                        binding.textViewCountReferencesAdded.text = it.references_added
                        binding.textViewCountViews.text = it.views
                        binding.textViewCountEditors.text = it.editor
                    }
        }
        return binding.root
    }
}
