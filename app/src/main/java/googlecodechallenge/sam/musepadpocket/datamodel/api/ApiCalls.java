package googlecodechallenge.sam.musepadpocket.datamodel.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.app.AppExecutor;
import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.RetrofitInterface;
import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.IApiCalls;
import googlecodechallenge.sam.musepadpocket.datamodel.database.MuseDatabase;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;
import googlecodechallenge.sam.musepadpocket.model.UserModel;
import googlecodechallenge.sam.musepadpocket.museViews.MuseListActivityFree;
import googlecodechallenge.sam.musepadpocket.networkutils.IRetrofitInstance;
import googlecodechallenge.sam.musepadpocket.networkutils.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalls implements IApiCalls {

    public Throwable throwable;

    private Context context;
    private String userName, password, url, email;
    UserModel userModel;


    public ApiCalls(Context context) {
        this.context = context;
    }

    public ApiCalls(UserModel userModel, Context context){
       this.userModel = userModel;
       this.context = context;
    }

    public ApiCalls(String userName, String password, Context context, String url) {
        this.userName = userName;
        this.password = password;
        this.url = url;
        this.context = context;

    }
    public ApiCalls(String userName, String password,
                    String email, Context context){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.context = context;
    }



    public RetrofitInterface getRetrofitInstance(){

        IRetrofitInstance retrofitInstance = new RetrofitInstance();
        return retrofitInstance.retrofitInstance(context);
    }

    @Override
    public boolean getMuseViewModel() {

        Call<ArrayList<MuseModel>> call = new ApiManager(getRetrofitInstance(),
                context).getMuseLists();
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
                Log.d("Error", "Data Not loaded");
            }
        });

        return true;

    }

    @Override
    public void register(){
        Call<UserModel> call = new
                ApiManager(userModel, getRetrofitInstance(),context).registerUser();

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.body() != null){
                    if (response.body().getMessage().equals("You have been successfully added as"+userName)){
                        Toast.makeText(context,"" +response.body().getMessage(),
                                Toast.LENGTH_LONG).show();
                        MuseDatabase museDatabase = MuseDatabase.getDatabase(context);
                        museDatabase.userDao().insertuser(userModel);

                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void login() {

        Call<UserModel> call = new ApiManager(userModel, getRetrofitInstance(),context)
                .login();

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {


                assert response.body() != null;

                try{
                if (response.body().getToken() != null) {

                    Toast.makeText(context, "Token info" + response.body().getToken(),
                            Toast.LENGTH_LONG).show();

                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", response.body().getToken());
                    editor.putString("userName",userModel.getUserName());
                    editor.apply();
                    Intent intent = new Intent(context, MuseListActivityFree.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Login not sucessful",
                            Toast.LENGTH_LONG).show();
                }
                } catch (Exception e){
                    Toast.makeText(context,"Incorrect Username or Password"
                            , Toast.LENGTH_LONG).show();

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
