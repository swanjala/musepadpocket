package googlecodechallenge.sam.musepadpocket.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import googlecodechallenge.sam.musepadpocket.R;

/**
 * Created by sam on 7/1/18.
 */

public class ViewNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        initRecyclerView(this);

    }

    @Override
    protected void onPause(){
        super.onPause();


    }

    @Override
    protected void onResume(){
        super.onResume();


    }
    private void initRecyclerView(Context context){
        RecyclerView mRecyclerView = findViewById(R.id.rc_muse_item_list_viewer);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        JSONArray data = getData();
        RecyclerView.Adapter mAdapter = new MuseListItemsAdapter(context, data);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private JSONArray getData(){

        JSONArray items = new JSONArray();

        final Bundle extras = getIntent().getExtras();


        try {
            items = new JSONArray(String.valueOf(extras.get("Data")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("items", String.valueOf(items));

        return items;

    }

}
