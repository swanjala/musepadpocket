package googlecodechallenge.sam.musepadpocket.museViews;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.adapters.MuseListAdapter;
import googlecodechallenge.sam.musepadpocket.fragments.MuseListFragment;
import googlecodechallenge.sam.musepadpocket.networkutils.ApiCalls;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;

/**
 * The main class displays all the muses from the api.
 */

public class MuseListActivityFree extends AppCompatActivity  implements View.OnClickListener{

    public static final String DATA = "DataDetails";

    private FragmentManager fragmentManager =
            getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_muse_list_layout);
        Bundle bundle = new Bundle();

        JSONArray data = getData();

        if(fragmentManager.getBackStackEntryCount() == 0
                && onSavedInstanceState == null ){
            MuseListFragment museListFragment = new MuseListFragment();
            bundle.putParcelable(DATA,data);
            museListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .add(R.id.listContainer, museListFragment)
                    .commit();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private JSONArray getData() {
        ApiCalls apiCalls = new ApiCalls();
        JSONArray jsonArrayMuseData = apiCalls.getMuseList(initUrlBuilder(), this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.muse_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if (menuItem.getItemId()==R.id.muse_list_filter){
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }


}
