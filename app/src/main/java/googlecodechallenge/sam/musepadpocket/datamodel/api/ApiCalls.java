package googlecodechallenge.sam.musepadpocket.datamodel.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.app.AppExecutor;
import googlecodechallenge.sam.musepadpocket.datamodel.database.MuseDatabase;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;
import googlecodechallenge.sam.musepadpocket.model.UserModel;
import googlecodechallenge.sam.musepadpocket.museViews.MuseListActivityFree;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponse {

    public ArrayList<MuseModel> museListData;
    public Throwable throwable;

    private Context context;
    private String userName, password,url;

    public ApiResponse(Context context){
        this.context = context;
    }
    public ApiResponse(String userName,String password,Context context, String url){
        this.userName = userName;
        this.password = password;
        this.url = url;
        this.context = context;
    }
    public boolean getMuseViewModel() {


        Call<ArrayList<MuseModel>> call = new ApiManager( context,
                context.getResources().getString(R.string.muse_base_url))
                .getMuseLists();
        call.enqueue(new Callback<ArrayList<MuseModel>>() {

            @Override
            public void onResponse(Call<ArrayList<MuseModel>> call, Response<ArrayList<MuseModel>> response) {
                final ArrayList<MuseModel> museListData = new ArrayList<>();

                museListData.addAll(response.body());

               AppExecutor.getDatabaseInstance().getDiskIO().execute(new Runnable() {
                   @Override
                   public void run() {
                       MuseDatabase museDatabase = MuseDatabase.getDatabase(context);
                       museDatabase.museListDao().insertMuse(museListData);

                   }
               });

                }

            @Override
            public void onFailure(Call<ArrayList<MuseModel>> call, Throwable t) {
                Log.d("Error","Data Not loaded");
            }
        });

        return true;

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
