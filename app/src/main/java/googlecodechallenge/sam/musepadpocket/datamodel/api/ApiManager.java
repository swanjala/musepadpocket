package googlecodechallenge.sam.musepadpocket.datamodel.api;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.model.ItemModel;
import googlecodechallenge.sam.musepadpocket.model.UserRequestModel;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;
import googlecodechallenge.sam.musepadpocket.model.UserModel;
import googlecodechallenge.sam.musepadpocket.networkutils.RetrofitInstance;
import retrofit2.Call;

public class ApiManager {

    private Context context;
    private String username, password, email;
    private  String url;
    UserModel userModel = new UserModel();

    ApiCallInstance apiCallInstance = new ApiCallInstance();

    public ApiManager(Context context, String url){
        this.url =url;
        this.context = context;
    }

    public ApiManager(String username, String password,
                      Context context){
        this.userModel.setUserName(username);
        this.userModel.setPassword(password);

        this.url = context.getResources().getString(R.string.muse_base_url);
        this.context =context;
        this.apiCallInstance.setApiInterface(RetrofitInstance.retrofitInstance(url,
                context));
    }

    public ApiManager( String name, String password,
                      String email, final Context context){
        this.context = context;
        this.username = name;
        this.password = password;
        this.email= email;

    }

    public ApiManager(String name, String password, Context context,
                      String url){
        this.username = name;
        this.password = password;
        this.url = url;
        this.context = context;
    }

    public Call<UserModel> login() {

        this.apiCallInstance.setApiInterface(RetrofitInstance
                .retrofitInstance(this.url,context));

        UserRequestModel loginModel = new UserRequestModel(this.username,this.password);

        return apiCallInstance.getApiInterface()
                .login(loginModel);
    }

    public Call<UserModel> registerUser(){

        this.apiCallInstance.setApiInterface(RetrofitInstance.retrofitInstance(context
                .getResources().getString(R.string.muse_base_url),context));

        UserRequestModel registrationModel =
                new UserRequestModel(this.username,this.password,email);

        return apiCallInstance.getApiInterface().registerUser(registrationModel);

    }

    @NonNull
    public Call<MuseModel> addMuse(String name){

        return apiCallInstance.getApiInterface()
                .addMuselist(new MuseModel(name));
    }

    @NonNull
    public Call<ItemModel> addItems(String name, String itemId){

        return  apiCallInstance.getApiInterface()
                .addItems(itemId, new ItemModel(name));
    }

    @NonNull
    public Call<ArrayList<MuseModel>> getMuseLists() {

        this.apiCallInstance.setApiInterface(RetrofitInstance
                .retrofitInstance(this.url, context));

        return apiCallInstance.getApiInterface()
                .getMuselist();
    }


}
