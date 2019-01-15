package googlecodechallenge.sam.musepadpocket.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

import butterknife.BindView;
import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.adapters.MuseListAdapter;

public class MuseListFragment extends Fragment {

//    @BindView(R.id.rc_muse_item_list_viewer)
//    RecyclerView recyclerView;
    private JSONArray museData;
    private static final String MUSE_DATA ="DataDetails";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,
                             Bundle savedInstanceState){
        final View rootView = layoutInflater.inflate(R.layout.fragment_muse_list,
                container,false);

        final RecyclerView recyclerView = rootView.findViewById(R.id.rc_muse_item_list_viewer);
        Bundle bundle = this.getArguments();

        museData = (JSONArray) bundle.get(MUSE_DATA);

        MuseListAdapter museListAdapter = new MuseListAdapter(getContext(),
                museData);
        recyclerView.setAdapter(museListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton floatingActionButton = rootView.findViewById(
                R.id.fb_add_muse);

        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0){
                    fragmentManager.popBackStack();
                }
            }
        });


        return rootView;

    }


}
