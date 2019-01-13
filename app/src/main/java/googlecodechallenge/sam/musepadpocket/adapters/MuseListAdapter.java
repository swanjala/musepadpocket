package googlecodechallenge.sam.musepadpocket.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.museViews.ViewNotesActivity;

/**
 * Created by sam on 7/1/18.
 */

public class MuseListAdapter extends
        RecyclerView.Adapter<MuseListAdapter.MuseListViewAdapter> {

    private JSONArray mDataSet;
    private LayoutInflater layoutInflater;
    private Context context;

    public MuseListAdapter(Context context, JSONArray museDataJsonArray) {
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
        return mDataSet.length();

    }

 class MuseListViewAdapter extends RecyclerView.ViewHolder {
     TextView tv_muse_name, tv_date_created;
     int position;

     private String museName;
     private String museDate;
     private JSONObject data;


     public MuseListViewAdapter(View museListView) {
         super(museListView);

         tv_muse_name = museListView.findViewById(R.id.tv_display_entry_text);
         tv_date_created=museListView.findViewById(R.id.tv_items_day_display);


     }

     public void setData(final JSONArray current, final int position) {

         try {
             this.data =  current.getJSONObject(position);

             this.museName = data.getString("name");
             this.museDate = data.getString("date_created");

         } catch (JSONException e) {
             e.printStackTrace();
         }
         this.tv_muse_name.setText(museName);
         tv_muse_name.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context,ViewNotesActivity.class);
                 try {
                     intent.putExtra("Data",data.getString("items"));
                     intent.putExtra("Id",data.getString("id"));
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
                 context.startActivity(intent);
             }
         });
         this.tv_date_created.setText(museDate);

         this.position = position;
     }


 }
}
