package googlecodechallenge.sam.musepadpocket.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.models.MuseModel;
import googlecodechallenge.sam.musepadpocket.museViews.ViewNotesActivity;

/**
 * Created by sam on 7/1/18.
 */

public class MuseListAdapter extends
        RecyclerView.Adapter<MuseListAdapter.MuseListViewAdapter> {

    private ArrayList<MuseModel> mDataSet;
    private LayoutInflater layoutInflater;
    private Context context;

    public MuseListAdapter(Context context, ArrayList<MuseModel> museDataJsonArray) {
        mDataSet = museDataJsonArray;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public MuseListViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.activity_muse_list_view_card_layout,
                parent, false);
        return new MuseListViewAdapter(view);

    }


    @Override
    public void onBindViewHolder(MuseListViewAdapter holder, int position) {

        holder.setData(mDataSet,position);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();

    }

 class MuseListViewAdapter extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_display_entry_text)
         TextView tv_muse_name;

        @BindView(R.id.tv_items_day_display)
        TextView tv_date_created;

        int position;

        private String museName;
        private String museDate;
        private MuseModel data;


     public MuseListViewAdapter(View museListView) {
         super(museListView);

         ButterKnife.bind(this, museListView);


     }

     public void setData(final ArrayList<MuseModel> current, final int position) {

         this.data =  current.get(position);

         this.museName = data.getName();

         this.museDate = data.getDateCreated();

         this.tv_muse_name.setText(museName);
         tv_muse_name.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context,ViewNotesActivity.class);
                 intent.putExtra("Data", data.getItems());
                 intent.putExtra("Id",data.getId());
                 context.startActivity(intent);
             }
         });
         this.tv_date_created.setText(museDate);

         this.position = position;
     }


 }
}
