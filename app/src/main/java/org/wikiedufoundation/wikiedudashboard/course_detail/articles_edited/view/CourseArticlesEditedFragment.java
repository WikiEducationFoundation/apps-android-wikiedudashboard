package org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.wikiedufoundation.wikiedudashboard.R;
import org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.data.ArticlesEdited;

import org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.presenter.ArticlesEditedPresenterImpl;
import org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.provider.RetrofitArticlesEditedProvider;
import org.wikiedufoundation.wikiedudashboard.helper.SharedPrefs;
import org.wikiedufoundation.wikiedudashboard.helper.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseArticlesEditedFragment extends Fragment implements ArticlesEditedView {


    private SharedPrefs sharedPrefs;
    private Context context;
    @BindView(R.id.tv_no_edited_articles)
    TextView tv_no_edited_articles;



    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.rv_edited_articles_list)
    RecyclerView recyclerView;

    private ArticlesEditedPresenterImpl articlesEditedPresenter;
    private ArticlesEditedRecyclerAdapter articlesEditedRecyclerAdapter;
    private String url;


    public CourseArticlesEditedFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_edited,container,false);
        ButterKnife.bind(this,view);
        url = getArguments().getString("url",null);
        context = getContext();
        sharedPrefs = new SharedPrefs(context);
        tv_no_edited_articles.setText(sharedPrefs.getCookies());
        articlesEditedPresenter = new ArticlesEditedPresenterImpl(new RetrofitArticlesEditedProvider(),this);

        articlesEditedRecyclerAdapter = new ArticlesEditedRecyclerAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(articlesEditedRecyclerAdapter);

        articlesEditedPresenter.requestArticlesEdited(url);
        return view;
    }

    @Override
    public void setData(ArticlesEdited data) {
        Log.d("ArticlesEditedFragment",data.toString());
        if(data.getCourse().getArticles().size()>0)
        {
            recyclerView.setVisibility(View.VISIBLE);
            articlesEditedRecyclerAdapter.setData(data.getCourse().getArticles());
            articlesEditedRecyclerAdapter.notifyDataSetChanged();
            tv_no_edited_articles.setVisibility(View.GONE);

        }
        else {
            recyclerView.setVisibility(View.GONE);
            tv_no_edited_articles.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void showProgressBar(boolean show) {
        if (show){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {
        ViewUtils.showToast(context, message);
    }

}
