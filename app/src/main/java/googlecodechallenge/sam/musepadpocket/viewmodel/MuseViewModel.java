package googlecodechallenge.sam.musepadpocket.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;


import java.util.List;

import googlecodechallenge.sam.musepadpocket.datamodel.database.MuseDatabase;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;

public class MuseViewModel extends AndroidViewModel {
    private static final String TAG = MuseViewModel.class.getSimpleName();
    private LiveData<List<MuseModel>>  museData;
    private  List<MuseModel> updatedData;

    public MuseViewModel(@NonNull Application application){
        super(application);

        MuseDatabase museDatabase = MuseDatabase.getDatabase(this.getApplication());
        Log.d(TAG, "Loading muses from database");
        museData = museDatabase.museListDao().fetchAll();
    }

    public  LiveData<List<MuseModel>> getMuses(){
        return museData;
    }


}
