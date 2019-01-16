package googlecodechallenge.sam.musepadpocket.museViews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.net.URL;
import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.api.ApiManager;
import googlecodechallenge.sam.musepadpocket.fragments.MuseListFragment;
import googlecodechallenge.sam.musepadpocket.models.MuseModel;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The main class displays all the muses from the api.
 */

public class MuseListActivityFree extends AppCompatActivity  implements View.OnClickListener{

    public static final String DATA = "DataDetails";

    private FragmentManager fragmentManager =
            getSupportFragmentManager();


    private ArrayList<MuseModel> museModelData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_muse_list_layout);
        Bundle bundle = new Bundle();

        getData();

        if(fragmentManager.getBackStackEntryCount() == 0
                && onSavedInstanceState == null ){
            MuseListFragment museListFragment = new MuseListFragment();
            bundle.putParcelableArrayList(DATA,museModelData);
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

    private void getData() {


        Call<ArrayList<MuseModel>> call = new ApiManager(this)
                .getMuseLists();
        call.enqueue(new Callback<ArrayList<MuseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MuseModel>> call, Response<ArrayList<MuseModel>> response) {
                museModelData.addAll(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<MuseModel>> call, Throwable t) {
                Log.d("Error","Data Not loaded");
            }
        });


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
