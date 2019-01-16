package googlecodechallenge.sam.musepadpocket.api;

import googlecodechallenge.sam.musepadpocket.networkutils.INetwork;

public class ApiCallInstance {
    private INetwork iNetwork;
    private String url;

    public INetwork getiNetwork(){
        return this.iNetwork;
    }
    public  void setiNetwork(INetwork iNetwork){
        this.iNetwork = iNetwork;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }
}
