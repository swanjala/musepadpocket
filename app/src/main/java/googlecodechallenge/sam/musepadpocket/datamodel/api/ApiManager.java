package googlecodechallenge.sam.musepadpocket.networkutils.api;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.models.DeleteMuse;
import googlecodechallenge.sam.musepadpocket.models.ItemModel;
import googlecodechallenge.sam.musepadpocket.models.LoginModel;
import googlecodechallenge.sam.musepadpocket.models.MuseModel;
import googlecodechallenge.sam.musepadpocket.models.UserModel;
import googlecodechallenge.sam.musepadpocket.networkutils.retrofit.RetrofitInstance;
import retrofit2.Call;

public class ApiManager {

    public static ArrayList<MuseModel> museModelArrayList;

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

    public ApiManager(final Context context, String name, String password,
                      String email){
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

        LoginModel loginModel = new LoginModel(this.username,this.password);

        return apiCallInstance.getApiInterface()
                .login(loginModel);
    }

    public Call<UserModel> registerUser(String username, String password,
                                        String email){
        return apiCallInstance.getApiInterface()
                .registerUser(new UserModel(username, password, email));

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
    public Call<DeleteMuse> deleteMuse(String id){

        return apiCallInstance
                .getApiInterface()
                .deleteMuseList(id);
    }

    @NonNull
    public Call<ArrayList<MuseModel>> getMuseLists() {

        this.apiCallInstance.setApiInterface(RetrofitInstance
                .retrofitInstance(this.url, context));

        return apiCallInstance.getApiInterface()
                .getMuselist();
    }


}
