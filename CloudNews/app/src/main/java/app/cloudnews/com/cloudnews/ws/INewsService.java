package app.cloudnews.com.cloudnews.ws;


import app.cloudnews.com.cloudnews.model.SourcesResponse;
import app.cloudnews.com.cloudnews.model.TopHeadlinesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lab08-15 on 24.12.2017.
 */

public interface INewsService {
    @GET("sources")
    public Call<SourcesResponse> getSources(@Query("apiKey")String apiKey);

    @GET("top-headlines")
    public Call<TopHeadlinesResponse> getTopHeadlines(@Query("sources") String sources,@Query("apiKey")String apiKey);

}
