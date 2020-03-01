package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.databinding.FragmentStudentDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class StudentDetailsFragment : BottomSheetDialogFragment() {

    private var username: String? = null
    private var edit_count: String? = null
    private var role: String? = null
    private var course: String? = null
    private lateinit var binding: FragmentStudentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_details,
                container, false)
        username = arguments?.getString("username")
        course = arguments?.getString("course")
        edit_count = arguments?.getString("edit_count")
        role = arguments?.getString("role")

        binding.textViewTitle.text = username
        binding.textViewCourseList.text = course
        binding.textViewEditCount.text = edit_count
        binding.textViewRole.text = role
        return binding.root
    }
}
