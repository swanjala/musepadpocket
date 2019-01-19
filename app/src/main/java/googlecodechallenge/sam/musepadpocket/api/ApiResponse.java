package googlecodechallenge.sam.musepadpocket.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.models.MuseModel;
import googlecodechallenge.sam.musepadpocket.models.UserModel;
import googlecodechallenge.sam.musepadpocket.museViews.MuseListActivityFree;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponse {

    public ArrayList<MuseModel> museListData;
    public Throwable throwable;

    private Context context;
    private String userName, password,url;

    public ApiResponse(Context context, String url){
        this.context = context;
        this.url = url;
    }
    public ApiResponse(String userName,String password,Context context, String url){
        this.userName = userName;
        this.password = password;
        this.url = url;
        this.context = context;
    }

    public void login(){

        Call<UserModel> call = new ApiManager(userName,password,context, url)
                .login();

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {


                if (response.body().getToken() != null){

                    Toast.makeText(context, "Token info" + response.body().getToken(),
                            Toast.LENGTH_LONG).show();

                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", response.body().getToken());
                    editor.apply();
                    Intent intent = new Intent(context,MuseListActivityFree.class);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, "Login not sucessful",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

                Log.d("Retrofit Error", t.getLocalizedMessage());

            }
        });

    }
    @NonNull
    public Throwable getThrowable() {
        return throwable;
    }

}
