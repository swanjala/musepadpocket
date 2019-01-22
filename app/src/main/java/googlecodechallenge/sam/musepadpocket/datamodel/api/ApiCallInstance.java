package googlecodechallenge.sam.musepadpocket.datamodel.api;

import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.ApiInterface;

public class ApiCallInstance {

    private ApiInterface apiInterface;

    public ApiInterface getApiInterface() {

        return this.apiInterface;
    }

    public void setApiInterface(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }
}
