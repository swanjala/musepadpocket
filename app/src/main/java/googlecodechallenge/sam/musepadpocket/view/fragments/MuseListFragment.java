package googlecodechallenge.sam.musepadpocket.view.fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;
import googlecodechallenge.sam.musepadpocket.viewmodel.MuseViewModel;

public class MuseListFragment extends Fragment {


    private static final String MUSE_DATA ="DataDetails";

    MuseListFragment.OnMuseListLoadedListener museListLoadedListener;

    public interface  OnMuseListLoadedListener{
        void onLoadMuseLists(List<MuseModel> data);
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

        if (savedInstanceState == null) {

            loadRecyclerView(recyclerView);
        }

        FloatingActionButton floatingActionButton = rootView.findViewById(
                R.id.fb_add_muse);

        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                showAddMuseInputDialog(getContext());
            }
        });

        return rootView;

    }

    protected  void showAddMuseInputDialog(final Context context) {

        View promptView = LayoutInflater
                .from(context)
                .inflate(R.layout.input_dialog, null);
        final EditText museName = promptView.findViewById(R.id.tv_dialog_muse_name);
        final EditText description = promptView.findViewById(R.id.tv_description);


        AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(context);
        alerDialogBuilder.setView(promptView);

        alerDialogBuilder.setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(context, "Added "+ museName.getText()
                                +" "+description.getText()+" successfully",Toast.LENGTH_LONG).show();

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alerDialogBuilder.create();
        alert.show();

    }
    private void loadRecyclerView(final RecyclerView recyclerView ) {
        MuseViewModel museViewModel = ViewModelProviders.of(this)
                .get(MuseViewModel.class);
        museViewModel.getMuses().observe(this, new Observer<List<MuseModel>>() {
            @Override
            public void onChanged(@Nullable List<MuseModel> museModelData) {

                FragmentAdapter fragmentAdapter = new FragmentAdapter(museModelData);
                recyclerView.setAdapter(fragmentAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        });
    }

    public class FragmentAdapter extends
            RecyclerView.Adapter<FragmentAdapter.ViewHolder> {

        private List<MuseModel> mDataSet;

        public FragmentAdapter(List<MuseModel> data){
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
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_muse_list_view_card_layout,
                            parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FragmentAdapter.ViewHolder holder,
                                     int position){
            holder.tv_muse_name.setText(String.valueOf(mDataSet.get(position).getName()));
            holder.tv_date_created.setText(String.valueOf(mDataSet.get(position).getDateCreated()));

        }

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

    }


}
