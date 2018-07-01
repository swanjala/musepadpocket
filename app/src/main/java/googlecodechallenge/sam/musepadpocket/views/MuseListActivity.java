package googlecodechallenge.sam.musepadpocket.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;

import java.net.URL;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.networkutils.ApiCalls;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;

/**
 * The main class displays all the muses from the api.
 */

public class MuseListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_muse_list_layout);
        initRecyclerView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView(this);
    }

    public void initRecyclerView(Context context) {

        RecyclerView mRecyclerView = findViewById(R.id.rc_muse_list_viewer);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        JSONArray data = getData();
        RecyclerView.Adapter mAdapter = new MuseListAdapter(context, data);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    private JSONArray getData() {
        ApiCalls apiCalls = new ApiCalls();
        JSONArray jsonArrayMuseData = apiCalls.getMuseList(initUrlBuilder(), this);

        Log.d("Data", String.valueOf(jsonArrayMuseData));
        return jsonArrayMuseData;
    }

    private URL initUrlBuilder() {
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForMuseActions();

        return url;
    }


}
