package org.wikiedufoundation.wikiedudashboard.course_detail.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.wikiedufoundation.wikiedudashboard.R;
import org.wikiedufoundation.wikiedudashboard.campaign.view.CampaignListFragment;
import org.wikiedufoundation.wikiedudashboard.course_detail.data.CourseDetail;
import org.wikiedufoundation.wikiedudashboard.course_detail.presenter.CourseDetailPresenter;
import org.wikiedufoundation.wikiedudashboard.course_detail.presenter.CourseDetailPresenterImpl;
import org.wikiedufoundation.wikiedudashboard.course_detail.provider.RetrofitCourseDetailProvider;
import org.wikiedufoundation.wikiedudashboard.course_list.view.CourseListFragment;
import org.wikiedufoundation.wikiedudashboard.home.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseDetailActivity extends AppCompatActivity implements CourseDetailView{

    private CourseDetailPresenter courseDetailPresenter;
    private String url;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        url = getIntent().getStringExtra("url");
        ButterKnife.bind(this);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabs();
        courseDetailPresenter = new CourseDetailPresenterImpl(this, new RetrofitCourseDetailProvider());
        courseDetailPresenter.requestCourseDetail(url);
    }

    @Override
    public void showProgressbar(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setData(CourseDetail data) {

    }

    private void setTabs() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        titleList.add("Active Campaigns");
        fragmentList.add(new CampaignListFragment());
        titleList.add("Active Courses");
        fragmentList.add(new CourseListFragment());
        viewPagerAdapter.setTabData(fragmentList, titleList);
        viewPagerAdapter.notifyDataSetChanged();
    }

}
