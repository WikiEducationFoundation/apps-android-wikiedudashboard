package com.codenicely.services.feelbazaar.vendorapp.course_detail.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.wikiedufoundation.wikiedudashboard.R;
import com.codenicely.services.feelbazaar.vendorapp.course_detail.data.CourseDetail;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link CourseHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseHomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private CourseDetail courseDetail;

    @BindView(R.id.tv_count_articles_created)
    TextView tv_count_articles_created;

    @BindView(R.id.tv_count_articles_edited)
    TextView tv_count_articles_edited;

    @BindView(R.id.tv_count_total_edits)
    TextView tv_count_total_edits;

    @BindView(R.id.tv_count_student_editors)
    TextView tv_count_student_editors;

    @BindView(R.id.tv_count_words_added)
    TextView tv_count_words_added;

    @BindView(R.id.tv_count_article_views)
    TextView tv_count_article_views;

    @BindView(R.id.tv_count_commons_uploads)
    TextView tv_count_commons_uploads;

    @BindView(R.id.tv_course_title)
    TextView tv_course_title;

    @BindView(R.id.tv_course_description)
    TextView tv_course_description;

    @BindView(R.id.tv_course_school)
    TextView tv_course_school;

    @BindView(R.id.tv_course_term)
    TextView tv_course_term;

    @BindView(R.id.tv_course_passcode)
    TextView tv_course_passcode;

    @BindView(R.id.tv_course_expected_students)
    TextView tv_course_expected_students;

    @BindView(R.id.tv_course_start)
    TextView tv_course_start;

    @BindView(R.id.tv_course_end)
    TextView tv_course_end;

    public CourseHomeFragment() {
        // Required empty public constructor
    }

    public static CourseHomeFragment newInstance(CourseDetail courseDetail) {
        CourseHomeFragment fragment = new CourseHomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, courseDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseDetail = (CourseDetail) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_home, container, false);
        ButterKnife.bind(this,view);
        tv_count_articles_created.setText(courseDetail.getCreated_count());
        tv_count_articles_edited.setText(courseDetail.getEdited_count());
        tv_count_total_edits.setText(courseDetail.getEdit_count());
        tv_count_student_editors.setText(MessageFormat.format("{0}", courseDetail.getStudent_count()));
        tv_count_words_added.setText(courseDetail.getWord_count());
        tv_count_article_views.setText(courseDetail.getView_count());
        tv_count_commons_uploads.setText(MessageFormat.format("{0}", courseDetail.getUpload_count()));
        tv_course_title.setText(courseDetail.getTitle());
        tv_course_description.setText(courseDetail.getDescription());
        tv_course_school.setText(courseDetail.getSchool());
        tv_course_term.setText(courseDetail.getTerm());
        tv_course_passcode.setText(courseDetail.getPasscode());
        tv_course_expected_students.setText(MessageFormat.format("{0}", courseDetail.getExpected_students()));
        tv_course_start.setText(courseDetail.getStart());
        tv_course_end.setText(courseDetail.getEnd());
        return view;
    }
}
