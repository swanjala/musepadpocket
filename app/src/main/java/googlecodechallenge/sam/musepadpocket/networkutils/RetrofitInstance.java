package googlecodechallenge.sam.musepadpocket.networkutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.ApiInterface;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static ApiInterface retrofitInstance(Context context){
        // implement the url by getting the context
        final SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request modifiedRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer "
                        + sharedPreferences.getString("token", ""))
                        .build();
                return chain.proceed(modifiedRequest);
            }
        };


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.muse_base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client);
        Retrofit retrofit = retrofitBuilder.build();

        return retrofit.create(ApiInterface.class);
    }
}
