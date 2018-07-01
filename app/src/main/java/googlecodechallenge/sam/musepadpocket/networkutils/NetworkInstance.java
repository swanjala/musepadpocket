package googlecodechallenge.sam.musepadpocket.networkutils;

import android.content.Context;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URL;

/**
 * Created by sam on 7/1/18.
 */

public class NetworkInstance {

    private HttpClient httpClient = new DefaultHttpClient();
    private URL url;
    public NetworkInstance(URL url){
        this.url = url;
    }
    public NetworkInstance(){

    }

    public HttpPost postMethodWithoutHeaders(){
        return new HttpPost(String.valueOf(url));
    }
    public HttpPost postMethodWithHeaders(String token){
        HttpPost httpPost = new HttpPost(String.valueOf(url));
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
        httpPost.addHeader("Authorization",token);
        return  httpPost;
    }
    public HttpGet getMethod(String token){
        HttpGet httpGet = new HttpGet(String.valueOf(url));
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpGet.addHeader("Authorization", token);
        return httpGet;
    }

}
