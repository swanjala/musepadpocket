package googlecodechallenge.sam.musepadpocket.datamodel.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

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
    private  String url;
    UserModel userModel = new UserModel();

    ApiCallInstance apiCallInstance;

    public ApiManager(ApiCallInstance apiCallInstance,Context context){
        this.url =url;
        this.context = context;
        this.apiCallInstance = apiCallInstance;
    }

    public ApiManager(ApiCallInstance apiCallInstance,UserModel userModel,
                      Context context){
        this.userModel = userModel;
        this.context =context;
        this.apiCallInstance = apiCallInstance;
    }

    public Call<UserModel> login() {

        UserRequestModel loginModel = new UserRequestModel(userModel);

        return apiCallInstance.getApiInterface()
                .login(loginModel);
    }

    public Call<UserModel> registerUser(){

        UserRequestModel registrationModel =
                new UserRequestModel(userModel);

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

        return apiCallInstance.getApiInterface()
                .getMuselist();
    }


}
