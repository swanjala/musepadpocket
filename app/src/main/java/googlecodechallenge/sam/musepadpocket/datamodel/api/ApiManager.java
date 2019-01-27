package googlecodechallenge.sam.musepadpocket.datamodel.api;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.RetrofitInterface;
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

    ApiCallInstance apiCallInstance = new ApiCallInstance();



    public ApiManager(RetrofitInterface retrofitInstance,Context context){

        this.context = context;
        this.apiCallInstance.setRetrofitInterface(retrofitInstance);
    }
    public ApiManager(UserModel userModel,
                      RetrofitInterface retrofitInterface, Context context){
        this.context = context;
        this.userModel = userModel;
        this.apiCallInstance.setRetrofitInterface(retrofitInterface);

    }

    @NonNull
    public Call<UserModel> login() {

        UserRequestModel loginModel = new UserRequestModel(userModel);

        return apiCallInstance.getRetrofitInterface()
                .login(loginModel);
    }

    @NonNull
    public Call<UserModel> registerUser(){

        UserRequestModel registrationModel =
                new UserRequestModel(userModel);

        return apiCallInstance.getRetrofitInterface().registerUser(registrationModel);

    }

    @NonNull
    public Call<MuseModel> addMuse(String name){

        return apiCallInstance.getRetrofitInterface()
                .addMuselist(new MuseModel(name));
    }

    @NonNull
    public Call<ItemModel> addItems(String name, String itemId){

        return  apiCallInstance.getRetrofitInterface()
                .addItems(itemId, new ItemModel(name));
    }

    public Call<ArrayList<MuseModel>> getMuseLists() {

        return apiCallInstance.getRetrofitInterface()
                .getMuselist();
    }


}
