package app.cloudnews.com.cloudnews.cloudNews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.cloudnews.com.cloudnews.R;
import app.cloudnews.com.cloudnews.adapter.SourcesAdapter;
import app.cloudnews.com.cloudnews.listener.NewListListener;
import app.cloudnews.com.cloudnews.model.Sources;
import app.cloudnews.com.cloudnews.model.SourcesResponse;

import app.cloudnews.com.cloudnews.ws.ApiClient;
import app.cloudnews.com.cloudnews.ws.INewsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.cloudnews.com.cloudnews.util.ProjectPreferences.setHeader;

public class MainActivity extends AppCompatActivity {
    private List<Sources> sourcesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SourcesAdapter sAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        getSources();

        setHeader(this);

    }
    public void getSources(){
        INewsService iService = ApiClient.getClient().create(INewsService.class);
        Call<SourcesResponse> call = iService.getSources("979a28cf3ddd46d69baa79a3d869489b");
        call.enqueue(new Callback<SourcesResponse>() {
            @Override
            public void onResponse(Call<SourcesResponse> call, Response<SourcesResponse> response) {
              SourcesResponse result = response.body();
                sourcesList = Arrays.asList(result.getSources());

                sAdapter = new SourcesAdapter(sourcesList, getApplicationContext(), new NewListListener() {
                    @Override
                    public void onDetail(View view, int position) {
                       Sources sources=sourcesList.get(position);
                        //Toast.makeText(getApplicationContext(),news.title,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),ViewNewsSources.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id",sources.getId());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(sAdapter);


            }

            @Override
            public void onFailure(Call<SourcesResponse> call, Throwable t) {


                 }
        });
    }
}