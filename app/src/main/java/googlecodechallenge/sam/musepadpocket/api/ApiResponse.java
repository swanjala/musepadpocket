package googlecodechallenge.sam.musepadpocket.api;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.models.MuseModel;

public class ApiResponse {

    private ArrayList<MuseModel> museListData;
    private Throwable throwable;

    ApiResponse(ArrayList<MuseModel> museListData,
                Throwable throwable){
        this.throwable = throwable;
        this.museListData = museListData;
    }

    ApiResponse(ArrayList<MuseModel> museListData){
        this.museListData = museListData;
    }

    ApiResponse(Throwable throwable){
        this.throwable = throwable;
    }

    @NonNull
    public ArrayList<MuseModel> getMuseListData(){
        return museListData;
    }
    @NonNull
    public Throwable getThrowable() {
        return throwable;
    }

}
