package app.cloudnews.com.cloudnews.ws;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import app.cloudnews.com.cloudnews.util.UnixEpochDateTypeAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiClient {
 
    public static final String BASE_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;
 
 
    public static Retrofit getClient() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, UnixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter())
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}