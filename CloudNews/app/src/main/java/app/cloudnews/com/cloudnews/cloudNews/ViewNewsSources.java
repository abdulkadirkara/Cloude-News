package app.cloudnews.com.cloudnews.cloudNews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.cloudnews.com.cloudnews.R;
import app.cloudnews.com.cloudnews.adapter.ArticlesAdapter;
import app.cloudnews.com.cloudnews.adapter.SourcesAdapter;
import app.cloudnews.com.cloudnews.listener.NewListListener;
import app.cloudnews.com.cloudnews.model.Articles;
import app.cloudnews.com.cloudnews.model.Sources;
import app.cloudnews.com.cloudnews.model.SourcesResponse;
import app.cloudnews.com.cloudnews.model.TopHeadlinesResponse;
import app.cloudnews.com.cloudnews.ws.ApiClient;
import app.cloudnews.com.cloudnews.ws.INewsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.cloudnews.com.cloudnews.util.ProjectPreferences.setHeader;

public class ViewNewsSources extends AppCompatActivity {

    ProgressDialog pd;
    private List<Articles> articlesList = new ArrayList<>();
    private ArticlesAdapter sAdapter;
   private RecyclerView recyclerView;
    private String articleid;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
      articleid=getIntent().getExtras().getString("id");
        getArticles();

        setHeader(this);



    }


    public void getArticles(){
        pd = new ProgressDialog(ViewNewsSources.this);
        pd.setMessage("Please Wait...");
        pd.show();
        INewsService iService = ApiClient.getClient().create(INewsService.class);
        Call<TopHeadlinesResponse> call = iService.getTopHeadlines(articleid,"979a28cf3ddd46d69baa79a3d869489b");
        call.enqueue(new Callback<TopHeadlinesResponse>() {
            @Override
            public void onResponse(Call<TopHeadlinesResponse> call, Response<TopHeadlinesResponse> response) {
                TopHeadlinesResponse result = response.body();
                articlesList = Arrays.asList(result.getArticles());
                sAdapter = new ArticlesAdapter(articlesList, getApplicationContext(), new NewListListener() {
                    @Override
                    public void onDetail(View view, int position) {

                        //Toast.makeText(getApplicationContext(),news.title,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),NewsDetails.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id",articlesList.get(position).getUrl());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(sAdapter);
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<TopHeadlinesResponse> call, Throwable t) {

            }

        });

    }
}
