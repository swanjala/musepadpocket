package googlecodechallenge.sam.musepadpocket.networkutils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import googlecodechallenge.sam.musepadpocket.SignInActivity;
import googlecodechallenge.sam.musepadpocket.models.UserModel;

/**
 * Created by sam on 7/1/18.
 */

public class ApiCalls {

    private HttpClient httpClient = new DefaultHttpClient();
    UserModel userModel;

    private HttpPost postObject;
    private HttpGet getObject;
    private String userName,password,email;
    URL url;

    public ApiCalls(URL url,String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.url = url;
    }
    public ApiCalls(){

    }

    public boolean registerUser(){

        NetworkInstance networkInstance = new NetworkInstance(this.url);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("username", userName));
        nameValuePair.add(new BasicNameValuePair("password", password));
        nameValuePair.add(new BasicNameValuePair("email", email));

        try {
            postObject = networkInstance.postMethodWithoutHeaders();
            postObject.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse response = httpClient.execute(postObject);
            String responseString = EntityUtils.toString(response.getEntity());
            if (responseString.contains("You have been successfully added")){

                return true;
            }
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String login(URL loginUrl,String userName, String password){

        NetworkInstance networkInstance = new NetworkInstance(loginUrl);

            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("username",userName));
            nameValuePair.add(new BasicNameValuePair("password",password));

            try{
                postObject =networkInstance.postMethodWithoutHeaders();
                postObject.setEntity(new UrlEncodedFormEntity(nameValuePair));
            }catch(UnsupportedEncodingException e){

                e.printStackTrace();
            }
            try {
                HttpResponse response = httpClient.execute(postObject);
                String responseString = EntityUtils.toString(response.getEntity());

                if (responseString.contains("Could not log you in, Check credentials")){
                    return "";
                }

                JSONObject jsonResponse = new JSONObject(responseString);
                String token = jsonResponse.getString("token");
                return token;


            }catch (ClientProtocolException e){
                Log.d("Error", e.getMessage());
            }catch (IOException e){

                e.printStackTrace();

            }catch (JSONException e){
                e.printStackTrace();
            }


        return "";
    }

    public JSONArray getMuseList(URL getMuseListUrl,Context context) {

        JSONArray jsonArray = new JSONArray();
        NetworkInstance networkInstance= new NetworkInstance(getMuseListUrl);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String token = "Bearer ".concat(sharedPreferences.getString("token",""));

        Log.d("BucketList Token", token);

        if (token.equals("")) {
            Toast.makeText(context,"You are not logged in, Try Again",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, SignInActivity.class);
            context.startActivity(intent);

        } else {

            HttpClient httpClient = new DefaultHttpClient();

            getObject = networkInstance.getMethod(token);

            try {
                HttpResponse response = httpClient.execute(getObject);
                String responseStr = EntityUtils.toString(response.getEntity());

                jsonArray = new JSONArray(responseStr);


            } catch (ClientProtocolException e) {

            } catch (IOException e) {

                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return jsonArray;
    }


//    public boolean createBucketList(String name){
//
//        token = getToken();
//        if (token.equals("")){
//            Log.d("Auth ", "Not Authorized, Login in again");
//            return false;
//        } else {
//
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/bucketlists");
//            httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
//            httpPost.addHeader("Authorization","Bearer "+token);
//
//            try {
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//                nameValuePairs.add(new BasicNameValuePair("name", name));
//                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//                HttpResponse response = httpclient.execute(httpPost);
//                int responseCode = response.getStatusLine().getStatusCode();
//
//                if (responseCode == 200){
//                    return true;
//                }else{
//                    return false;
//                }
//
//
//            } catch (ClientProtocolException e) {
//                return false;
//
//            } catch (IOException e) {
//                Log.d("Exception",e.getLocalizedMessage());
//
//            }
//
//        }
//        return false;
//    }
//    public boolean editBucketList(int id, String newName){
//
//        if (token.equals("")){
//            Log.d("Auth : ", "Not Authorized");
//            return false;
//        }else {
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPut httpPut = new HttpPut("http://10.0.2.2:5000/api/v1/bucketlists/"+id);
//
//            try {
//
//                httpPut.addHeader("Content-Type","application/x-www-form-urlencoded");
//                httpPut.addHeader("Authorization","Bearer "+token);
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//                nameValuePairs.add(new BasicNameValuePair("name", newName));
//                httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//                HttpResponse response = httpclient.execute(httpPut);
//                int responseCode = response.getStatusLine().getStatusCode();
//
//                /*
//                * TODO
//                * Debug API HTTP Responses */
//
//                if (responseCode == 200){
//                    return true;
//                }else if (responseCode == 201){
//                    return true;
//                }
//
//            } catch (ClientProtocolException e) {
//
//            } catch (IOException e) {
//
//            }
//
//        }
//
//        return false;
//    }
//    public boolean deleteBucketList(int id){
//        if (token.equals("")){
//            Log.d("Auth : ", "Not Authorized");
//            return false;
//        }else {
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpDelete httpDelete = new HttpDelete("http://10.0.2.2:5000/api/v1/bucketlists/"+id);
//
//            try {
//
//                httpDelete.addHeader("Content-Type","application/x-www-form-urlencoded");
//                httpDelete.addHeader("Authorization","Bearer "+token);
//                HttpResponse response = httpclient.execute(httpDelete);
//                int responseCode = response.getStatusLine().getStatusCode();
//
//                if (responseCode == 200){
//                    return true;
//                }
//
//            } catch (ClientProtocolException e) {
//
//            } catch (IOException e) {
//
//            }
//
//        }
//        return false;
//    }
//    public boolean createItem(List<ItemFields> newItems) {
//        token = getToken();
//        String name = newItems.get(0).getItemName();
//
//        if (token.equals("")) {
//            Log.d("Auth ", "Not Authorized, Login in again");
//            return false;
//        } else {
//
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost("http://10.0.2.2:5000/api/v1/items");
//
//            try {
//
//                httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//                httpPost.addHeader("Authorization", "Bearer " + token);
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//                nameValuePairs.add(new BasicNameValuePair("name", name));
//                nameValuePairs.add(new BasicNameValuePair("status", "false"));
//                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//                HttpResponse response = httpclient.execute(httpPost);
//                int responseCode = response.getStatusLine().getStatusCode();
//
//                if (responseCode == 200) {
//                    return true;
//                }
//
//            } catch (ClientProtocolException e) {
//
//            } catch (IOException e) {
//
//            }
//            return false;
//        }
//    }
//    public boolean editItem(int id,String newItemName){
//        if (token.equals("")){
//            Log.d("Auth : ", "Not Authorized");
//            return false;
//        }else {
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPut httpPut = new HttpPut("http://10.0.2.2:5000/api/v1/items/"+id);
//
//            try {
//
//                httpPut.addHeader("Content-Type","application/x-www-form-urlencoded");
//                httpPut.addHeader("Authorization","Bearer "+token);
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//                nameValuePairs.add(new BasicNameValuePair("name", newItemName));
//                httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//                HttpResponse response = httpclient.execute(httpPut);
//                int responseCode = response.getStatusLine().getStatusCode();
//
//                if (responseCode == 200){
//                    return true;
//                }
//
//            } catch (ClientProtocolException e) {
//
//            } catch (IOException e) {
//
//            }
//
//        }
//
//        return false;
//    }
}
