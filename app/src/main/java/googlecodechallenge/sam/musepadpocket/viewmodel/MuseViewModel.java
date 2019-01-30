package googlecodechallenge.sam.musepadpocket.viewmodel;

import android.app.Application;
import android.app.Fragment;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;


import java.util.List;
import java.util.concurrent.CompletableFuture;

import googlecodechallenge.sam.musepadpocket.datamodel.database.MuseDatabase;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;
import googlecodechallenge.sam.musepadpocket.model.UserModel;

public class MuseViewModel extends AndroidViewModel {
    private static final String TAG = MuseViewModel.class.getSimpleName();
    private LiveData<List<MuseModel>>  museData;

    public MuseViewModel(@NonNull Application application){
        super(application);


        Log.d(TAG, "Loading muses from database");

        final MuseDatabase museDatabase = MuseDatabase.getDatabase(this.getApplication());
        museData = museDatabase.museListDao().fetchAll();

   }

    public  LiveData<List<MuseModel>> getMuses(){

        return museData;
    }


}
