package googlecodechallenge.sam.musepadpocket.museViews;

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

import org.json.JSONArray;
import org.json.JSONException;

import butterknife.BindView;
import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.adapters.MuseListItemsAdapter;

/**
 * Created by sam on 7/1/18.
 */

public class ViewNotesActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.fb_add_muse_notes)
    FloatingActionButton bt_add_new_note_item;

    @BindView(R.id.rc_muse_item_list_viewer)
    private RecyclerView mRecyclerView;

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        this.extras = getIntent().getExtras();

        initViewItems(this);

    }

    @Override
    protected void onPause(){
        super.onPause();


    }

    @Override
    protected void onResume(){
        super.onResume();
        initViewItems(this);

    }
    private void initViewItems(Context context){

        bt_add_new_note_item.setOnClickListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(mLayoutManager);

        getData();
    }

    private void getData(){

        JSONArray items = new JSONArray();

        try {
            items = new JSONArray(String.valueOf(extras.get("Data")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecyclerView.Adapter mAdapter = new MuseListItemsAdapter(this, items);

        this.mRecyclerView.setAdapter(mAdapter);

        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onClick(View view){
        int viewId = view.getId();
        if (viewId == R.id.fb_add_muse_notes){
            Intent intent = new Intent(this, AddNoteActivity.class);
            intent.putExtra("Id",extras.getString("Id"));
            startActivity(intent);
        }
    }

}
