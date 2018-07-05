package googlecodechallenge.sam.musepadpocket.views;

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

/**
 * Creates an adapter that lists muses.
 */

public class MuseListItemsAdapter extends RecyclerView.
        Adapter<MuseListItemsAdapter.MuseListItemsViewAdapter> {

    private JSONArray mDataSet;
    private LayoutInflater layoutInflater;
    private Context context;


    public MuseListItemsAdapter(Context context, JSONArray museItemsDataJsonObject){
        this.mDataSet = museItemsDataJsonObject;
        this.layoutInflater= LayoutInflater.from(context);
        this.context = context;

    }
    @Override
    public MuseListItemsViewAdapter onCreateViewHolder(ViewGroup parent, int viewType){
        View view = layoutInflater.inflate(R.layout.activity_muse_item_list_card_layout,parent, false);
        MuseListItemsViewAdapter viewHolder= new MuseListItemsViewAdapter(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MuseListItemsViewAdapter holder, int position){
        holder.setData(mDataSet,position);
    }

    @Override
    public int getItemCount(){
        return this.mDataSet.length();
    }

    class MuseListItemsViewAdapter extends RecyclerView.ViewHolder{
        TextView tv_muse_item_name, tv_muse_item_date_created;
        int position;

        private String museItemName;
        private String museItemDate;
        private JSONObject data;
        private String description;
        private String museId;
        private String noteId;
        public MuseListItemsViewAdapter(View musetItemListView){
            super(musetItemListView);

            tv_muse_item_name = musetItemListView.findViewById(R.id.tv_items_display_entry_text);
            tv_muse_item_date_created= musetItemListView.findViewById(R.id.tv_items_day_display);
        }

        public void setData(final JSONArray current, final int position){

            try {
                    this.data =current.getJSONObject(position);

                    this.museItemName = data.getString("name");
                    this.museItemDate = data.getString("date_created");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.tv_muse_item_name.setText(museItemName);
                tv_muse_item_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, EditNoteActivity.class);
                        try {
                            description = data.getString("item_description");
                            museId =data.getString("muse_id");
                            noteId = data.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                            intent.putExtra("Description",description);
                            intent.putExtra("MuseId",museId);
                            intent.putExtra("NoteId",noteId);



                        context.startActivity(intent);
                    }
                });
                this.tv_muse_item_date_created.setText(museItemName);

                this.position = position;


            }
        }

    }

