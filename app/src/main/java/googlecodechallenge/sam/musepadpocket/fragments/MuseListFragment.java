package googlecodechallenge.sam.musepadpocket.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.models.MuseModel;

public class MuseListFragment extends Fragment {


    private ArrayList<MuseModel> museData;
    private static final String MUSE_DATA ="DataDetails";

    MuseListFragment.OnMuseListLoadedListener museListLoadedListener;

    public interface  OnMuseListLoadedListener{
        void onLoadMuseLists(ArrayList<MuseModel> data);
    }
    public MuseListFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, final ViewGroup container,
                             Bundle savedInstanceState){

        final View rootView = layoutInflater.inflate(R.layout.fragment_muse_list,
                container,false);

        final RecyclerView recyclerView = rootView.findViewById(R.id.rc_muse_list_viewer);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            museData = bundle.getParcelableArrayList(MUSE_DATA);
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(museData);
        recyclerView.setAdapter(fragmentAdapter);
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

                Toast.makeText(getContext(),"This is somethings",Toast.LENGTH_LONG).show();
            }
        });


        return rootView;

    }

    public class FragmentAdapter extends
            RecyclerView.Adapter<FragmentAdapter.ViewHolder> {

        private ArrayList<MuseModel> mDataSet;
        private LayoutInflater layoutInflater;
        private Context context;

        public FragmentAdapter(ArrayList<MuseModel> data){
            this.mDataSet = data;
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_muse_name;

            TextView tv_date_created;

            public ViewHolder(View itemView){

                super(itemView);
                this.tv_muse_name = itemView.findViewById(R.id.tv_display_entry_text);
                this.tv_date_created = itemView.findViewById(R.id.tv_items_day_display);
            }

        }
        @Override
        public FragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = layoutInflater.inflate(R.layout.activity_muse_list_view_card_layout,
                    parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FragmentAdapter.ViewHolder holder,
                                     int position){
            holder.tv_muse_name.setText(String.valueOf(mDataSet.get(position).getName()));
            holder.tv_date_created.setText(String.valueOf(mDataSet.get(position).getDateCreated()));
            museListLoadedListener.onLoadMuseLists(museData);
        }

        @Override
        public int getItemCount() {
          return mDataSet.size();
        }

    }


}
