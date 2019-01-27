package googlecodechallenge.sam.musepadpocket.view.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.model.MuseModel;

public class FragmentAdapter extends
        RecyclerView.Adapter<FragmentAdapter.ViewHolder> {

    private List<MuseModel> mDataSet;

    public FragmentAdapter(){}
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
