package googlecodechallenge.sam.musepadpocket.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;

import java.net.URL;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.networkutils.ApiCalls;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;

/**
 * The main class displays all the muses from the api.
 */

public class MuseListActivity extends AppCompatActivity  implements View.OnClickListener{
  FloatingActionButton fb_add_new_muse;

    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_muse_list_layout);
        initViews(this);


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initViews(this);
    }

    public void initViews(Context context) {

        fb_add_new_muse = findViewById(R.id.fb_add_muse);
        fb_add_new_muse.setOnClickListener(this);

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

    private void addMuse(){
       Intent intent = new Intent(this, AddMuseActivity.class);
       startActivity(intent);

    }

    private URL initUrlBuilder() {
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForMuseActions();

        return url;
    }


    @Override
    public void onClick(View view){
        int viewId = view.getId();

        if(viewId == R.id.fb_add_muse){
            addMuse();
        }
    }


}
