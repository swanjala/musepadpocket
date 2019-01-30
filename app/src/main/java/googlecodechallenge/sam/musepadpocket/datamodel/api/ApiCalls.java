package googlecodechallenge.sam.musepadpocket.datamodel.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.app.AppExecutor;
import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.ApiInterface;
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
    private String userName, password, email;


    public ApiCalls(Context context) {
        this.context = context;
    }

    public ApiCalls(@NonNull UserModel userModel,
                    @NonNull Context context) {

        this.userName = userModel.getUserName();
        this.password = userModel.getPassword();

        if(userModel.getEmail() != null){
            this.email = userModel.getEmail();
        }else {
            this.email = "";
        }
        this.context = context;

    }


    @Override
    public boolean getMuseViewModel() {

        ApiCallInstance apiCallInstance = new
                ApiCallInstance(RetrofitInstance.retrofitInstance(context));

        Call<ArrayList<MuseModel>> call = new ApiManager(apiCallInstance,context)
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
                Log.d("Error", "Data Not loaded");
            }
        });

        return true;

    }

    @Override
    public void register(){
        ApiCallInstance apiCallInstance = new ApiCallInstance(RetrofitInstance
                .retrofitInstance(context));

        UserModel userModel = new UserModel(userName, password, email);
        Call<UserModel> call = new
                ApiManager(apiCallInstance,userModel, context).registerUser();

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.body() != null){
                    if (response.code() == 200){
                        Toast.makeText(context,"" +
                                response.body().getMessage(), Toast.LENGTH_LONG).show();
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

        UserModel userModel = new UserModel(userName, password);

        ApiCallInstance apiCallInstance = new ApiCallInstance(RetrofitInstance
                .retrofitInstance(context));

        Call<UserModel> call = new ApiManager(apiCallInstance,userModel, context)
                .login();

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                try {

                    if (response.code() == 404) {
                        Toast.makeText(context, "Unable to reach the muse pad server" +
                                " try again later", Toast.LENGTH_LONG).show();

                    } else if(response.code() == 200){
                        Toast.makeText(context, "Token info" + response.body().getToken(),
                                Toast.LENGTH_LONG).show();

                        SharedPreferences sharedPreferences = PreferenceManager
                                .getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", response.body().getToken());
                        editor.apply();
                        Intent intent = new Intent(context, MuseListActivityFree.class);
                        context.startActivity(intent);
                    }
                    else {
                        Toast.makeText(context, "Unable to log you in.",
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
