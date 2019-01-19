package googlecodechallenge.sam.musepadpocket.api;

public class ApiCallInstance {
    private ApiInterface apiInterface;

    private String url;

    public ApiInterface getApiInterface(){

        return this.apiInterface;
    }
    public  void setApiInterface(ApiInterface apiInterface){
        this.apiInterface = apiInterface;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }
}
