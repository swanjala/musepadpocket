package googlecodechallenge.sam.musepadpocket.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import googlecodechallenge.sam.musepadpocket.datamodel.api.ApiCallInstance;
import googlecodechallenge.sam.musepadpocket.datamodel.api.ApiCalls;
import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.IApiCalls;
import googlecodechallenge.sam.musepadpocket.model.UserModel;

public class UserViewModel extends AndroidViewModel {

    private static final String TAG = UserViewModel.class.getSimpleName();

    public UserViewModel(Application application){
        super(application);

    }

    public void loginUser(String userName,String password){

        UserModel userModel = new UserModel(userName,password);

        IApiCalls apiCalls = new ApiCalls(userModel,
                getApplication().getApplicationContext());
        apiCalls.login();
    }

    public void registerUser(String userName, String password,
                             String email){
        UserModel userModel = new UserModel(userName, password, email);
        IApiCalls apiCalls = new ApiCalls(userModel,
                getApplication().getApplicationContext());
        apiCalls.register();
    }
}
