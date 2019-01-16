package googlecodechallenge.sam.musepadpocket.api;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.models.DeleteMuse;
import googlecodechallenge.sam.musepadpocket.models.ItemModel;
import googlecodechallenge.sam.musepadpocket.models.MuseModel;
import googlecodechallenge.sam.musepadpocket.models.UserModel;
import googlecodechallenge.sam.musepadpocket.networkutils.RetrofitInstance;
import retrofit2.Call;

public class ApiManager {

    public static ArrayList<MuseModel> museModelArrayList;

    private Context context;
    private String username, password, email;
    private  String url;
    UserModel userModel = new UserModel();

    ApiCallInstance apiCallInstance = new ApiCallInstance();

    public ApiManager(Context context){
        this.url = context.getResources()
                .getString(R.string.muse_base_url);
        this.apiCallInstance.setiNetwork(RetrofitInstance
                .retrofitInstance(this.url, context));
        this.context = context;
    }

    public ApiManager(String username, String password,
                      Context context){
        this.userModel.setUserName(username);
        this.userModel.setPassword(password);

        this.url = context.getResources().getString(R.string.muse_base_url);
        this.context =context;
        this.apiCallInstance.setiNetwork(RetrofitInstance.retrofitInstance(url,
                context));
    }

    public ApiManager(final Context context, String name, String password,
                      String email){
        this.context = context;
        this.username = name;
        this.password = password;
        this.email= email;

    }

    @NonNull
    public Call<UserModel> login() {

        return apiCallInstance.getiNetwork()
                .login(new UserModel(userModel.getUserName(),
                userModel.getPassword()));
    }

    @NonNull
    public Call<UserModel> registerUser(String username, String password,
                                        String email){
        return apiCallInstance.getiNetwork()
                .registerUser(new UserModel(username, password, email));

    }

    @NonNull
    public Call<MuseModel> addMuse(String name){

        return apiCallInstance.getiNetwork()
                .addMuselist(new MuseModel(name));
    }

    @NonNull
    public Call<ItemModel> addItems(String name, String itemId){

        return  apiCallInstance.getiNetwork()
                .addItems(itemId, new ItemModel(name));
    }

    @NonNull
    public Call<DeleteMuse> deleteMuse(String id){

        return apiCallInstance
                .getiNetwork()
                .deleteMuseList(id);
    }

    public Call<ArrayList<MuseModel>> getMuseLists() {

        return apiCallInstance.getiNetwork()
                .getMuselist();
    }


}
