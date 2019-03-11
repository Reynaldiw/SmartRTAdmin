package com.reynaldiwijaya.smartrtadmin.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrtadmin.Adapter.NewsAdapter;
import com.reynaldiwijaya.smartrtadmin.Model.Informasi.NewsItem;
import com.reynaldiwijaya.smartrtadmin.Presenter.News.NewsContract;
import com.reynaldiwijaya.smartrtadmin.Presenter.News.NewsPresenter;
import com.reynaldiwijaya.smartrtadmin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class NewsConfirmationActivity extends AppCompatActivity implements NewsContract.View {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.tv_pesan)
    TextView tvPesan;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private List<NewsItem> newsItems = new ArrayList<>();
    private NewsPresenter newsPresenter = new NewsPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_confirmation);
        ButterKnife.bind(this);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsItems.clear();
                newsPresenter.getNews();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        newsItems.clear();
        newsPresenter.getNews();

        Log.i("Resume", "MASUK");

    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showDataList(List<NewsItem> newsItemList) {
        newsItems.addAll(newsItemList);
        initAdapter();
    }

    private void initAdapter() {

        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setAdapter(new NewsAdapter(this, newsItems));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPesan() {
        initAdapter();
        tvPesan.setVisibility(View.VISIBLE);
    }
}
