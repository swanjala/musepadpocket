package googlecodechallenge.sam.musepadpocket.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.List;

import googlecodechallenge.sam.musepadpocket.app.AppExecutor;
import googlecodechallenge.sam.musepadpocket.datamodel.database.MuseDatabase;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;
import googlecodechallenge.sam.musepadpocket.model.UserModel;

public class UserViewModel extends AndroidViewModel {

    private List<UserModel> userData;

    public UserViewModel(@NonNull final Application application){
        super(application);

        final MuseDatabase museDatabase = MuseDatabase.getDatabase(this.getApplication());


        AppExecutor.getDatabaseInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(application.getApplicationContext());
                String userName = sharedPreferences.getString("userName","");
                userData = museDatabase.userDao().getUsers(userName);
            }
        });


    }

    public List<UserModel> getUser(){


        return userData;
    }
}
