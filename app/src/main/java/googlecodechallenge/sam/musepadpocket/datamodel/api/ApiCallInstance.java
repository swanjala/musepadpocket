package googlecodechallenge.sam.musepadpocket.datamodel.api;

import android.support.annotation.NonNull;

import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.RetrofitInterface;

public class ApiCallInstance {


    private RetrofitInterface retrofitInterface;

    public void setRetrofitInterface(RetrofitInterface retrofitInstance) {
        this.retrofitInterface = retrofitInstance;
    }

    @NonNull
    public RetrofitInterface getRetrofitInterface() {

        return this.retrofitInterface;
    }


}
