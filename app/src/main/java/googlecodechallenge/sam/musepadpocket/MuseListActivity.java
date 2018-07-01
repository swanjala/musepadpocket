package googlecodechallenge.sam.musepadpocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;

import java.net.URL;

import googlecodechallenge.sam.musepadpocket.networkutils.ApiCalls;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;

/**
 * Created by sam on 6/27/18.
 */

public class MuseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_muse_list_layout);

        getData();

    }

    @Override
    protected void onPause(){
        super.onPause();

    }
    @Override
    protected void onResume(){
        super.onResume();
        getData();
    }


    private JSONArray getData(){
        ApiCalls apiCalls = new ApiCalls();
        JSONArray jsonArrayMuseData = apiCalls.getMuseList(initUrlBuilder(), this);

        Log.d("Data", String.valueOf(jsonArrayMuseData)) ;
        return jsonArrayMuseData;
    }
    private URL initUrlBuilder(){
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForMuseActions();

        return url;
    }


}
