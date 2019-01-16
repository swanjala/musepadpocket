package googlecodechallenge.sam.musepadpocket.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

public class MuseModel implements Parcelable {

    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("items")
    @Expose
    private ArrayList<ItemModel> items = null;
    @SerializedName("name")
    @Expose
    private String name;

    public MuseModel(String name){
        this.name = name;

    }
    protected  MuseModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        items = in.readArrayList(ItemModel.class.getClassLoader());
        dateCreated = in.readString();
        creator = in.readString();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<ItemModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemModel> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(creator);
        parcel.writeString(dateCreated);
        parcel.writeList(items);

    }
    public static final Creator<MuseModel> CREATOR = new Parcelable
            .Creator<MuseModel>(){

        @Override
        public MuseModel createFromParcel(Parcel in){
            return new MuseModel(in);
        }
        @Override
        public MuseModel[] newArray(int size){
            return new MuseModel[size];
        }
    };


}

