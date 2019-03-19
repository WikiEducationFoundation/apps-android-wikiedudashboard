package org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.wikiedufoundation.wikiedudashboard.R;
import org.wikiedufoundation.wikiedudashboard.course_detail.articles_edited.data.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesEditedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Article> edited = new ArrayList<>();


    public ArticlesEditedRecyclerAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view1 = layoutInflater.inflate(R.layout.item_rv_articles_edited,viewGroup,false);
        return new ArticlesEditedViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
      final ArticlesEditedViewHolder articlesEditedViewHolder = (ArticlesEditedViewHolder) viewHolder;
      articlesEditedViewHolder.tv_count_articles_edited_title.setText(edited.get(i).getTitle());

    }

    void setData(List<Article> edited) {
        this.edited = edited;
    }

    @Override
    public int getItemCount() {
        return edited.size();
    }

    public class ArticlesEditedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_count_articles_edited_title)
        TextView tv_count_articles_edited_title;

        public ArticlesEditedViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
