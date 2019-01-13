package googlecodechallenge.sam.musepadpocket.networkutils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.userViews.SignInActivity;

/**
 * Created by sam on 7/1/18.
 */

public class ApiCalls {

    URL url;
    private HttpClient httpClient = new DefaultHttpClient();
    private HttpPost postObject;
    private HttpPut putObject;
    private HttpGet getObject;
    private String userName, password, email;

    public ApiCalls(URL url, String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.url = url;
    }

    public ApiCalls() {

    }

    public boolean registerUser() {

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
            if (responseString.contains("You have been successfully added")) {

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

    public String login(URL loginUrl, String userName, String password) {

        NetworkInstance networkInstance = new NetworkInstance(loginUrl);

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("username", userName));
        nameValuePair.add(new BasicNameValuePair("password", password));

        try {
            postObject = networkInstance.postMethodWithoutHeaders();
            postObject.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(postObject);
            String responseString = EntityUtils.toString(response.getEntity());

            if (responseString.contains("Could not log you in, Check credentials")) {
                return "";
            }

            JSONObject jsonResponse = new JSONObject(responseString);
            String token = jsonResponse.getString("token");
            return token;


        } catch (ClientProtocolException e) {
            Log.d("Error", e.getMessage());
        } catch (IOException e) {

            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return "";
    }

    public void addMuse(URL url, String muse_name, String muse_description, String token, Context context) {

        NetworkInstance networkInstance = new NetworkInstance(url);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("name", muse_name));
        nameValuePair.add(new BasicNameValuePair("description", muse_description));


        String accessToken = "Bearer ".concat(token);

        try {
            postObject = networkInstance.postMethodWithHeaders(accessToken);
            postObject.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse response = httpClient.execute(postObject);
            String responseString = EntityUtils.toString(response.getEntity());
            Log.d("response ", responseString);
            if (responseString.equals("{\n" +
                    "                \"message\": \"" + muse_name + "\"Has been saved successfully\"\n" +
                    "            }")) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                sharedPreferences.edit().putBoolean("addMuse", true).apply();
            }
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public JSONArray getMuseList(URL getMuseListUrl, Context context) {

        JSONArray jsonArray = new JSONArray();
        NetworkInstance networkInstance = new NetworkInstance(getMuseListUrl);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String token = "Bearer ".concat(sharedPreferences.getString("token", ""));

        if (token.equals("")) {
            Toast.makeText(context, R.string.error_logginin, Toast.LENGTH_LONG).show();
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


    public void addNote(URL url, String entry, String item_name, String token, Context context) {

        NetworkInstance networkInstance = new NetworkInstance(url);

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("name", item_name));
        nameValuePair.add(new BasicNameValuePair("description", entry));

        try {
            postObject = networkInstance.postMethodWithHeaders(token);
            postObject.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(postObject);
            String responseString = EntityUtils.toString(response.getEntity());

            if (responseString.contains("item has been added")) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                sharedPreferences.edit().putBoolean("AddNote", false);

            }


        } catch (ClientProtocolException e) {
            Log.d("Error", e.getMessage());
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void editNote(URL url, String entry, String item_name, Context context) {

        NetworkInstance networkInstance = new NetworkInstance(url);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("name", item_name));
        nameValuePair.add(new BasicNameValuePair("description", entry));


        try {

            String token = "Bearer ".concat(sharedPreferences.getString("token", ""));
            putObject = networkInstance.putMethodWithoutHeaders(token);
            putObject.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(putObject);
            String responseString = EntityUtils.toString(response.getEntity());

            if (responseString.contains("item has been updated")) {
                sharedPreferences.edit().putBoolean("EditNote", true);
            }


        } catch (ClientProtocolException e) {
            Log.d("Error", e.getMessage());
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
