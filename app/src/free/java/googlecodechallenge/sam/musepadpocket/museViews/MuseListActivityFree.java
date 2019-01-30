package googlecodechallenge.sam.musepadpocket.museViews;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.datamodel.api.ApiCalls;
import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.IApiCalls;
import googlecodechallenge.sam.musepadpocket.view.fragments.MuseListFragment;

/**
 * The main class displays all the muses from the api.
 */

public class MuseListActivityFree extends AppCompatActivity  implements View.OnClickListener {

    private FragmentManager fragmentManager =
            getSupportFragmentManager();
    private static final String TAG = "MuseListActivityFree";

    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_muse_list_layout);

        if (onSavedInstanceState == null) {
            new viewLoader().execute();
        }

    }

    @Override
    public void onClick(View view){
        int viewId = view.getId();

        if(viewId == R.id.fb_add_muse){
            addMuse();
        }
    }

    private void addMuse(){ }

    private void fetchMuses() {

        if (fragmentManager.getBackStackEntryCount() == 0 ) {
            MuseListFragment museListFragment = new MuseListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.listContainer, museListFragment)
                    .commitAllowingStateLoss();
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

    public class viewLoader extends AsyncTask<Context,Void,Boolean>{

        @Override
        protected Boolean doInBackground(Context... context){
            IApiCalls apiCalls = new ApiCalls(getApplicationContext());

            return apiCalls.getMuseViewModel();
        }
        @Override
        protected void onPostExecute(Boolean onExecution){
            fetchMuses();
        }
    }


}
