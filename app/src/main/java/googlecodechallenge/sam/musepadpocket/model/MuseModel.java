package googlecodechallenge.sam.musepadpocket.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "muselist",
        foreignKeys = @ForeignKey(entity = UserModel.class,
                parentColumns = "id",
                childColumns = "uId",
                onDelete = CASCADE))
public class MuseModel implements Parcelable {


    @SerializedName("uId")
    @Expose
    private Integer uId;

    @SerializedName("creator")
    @Expose
    private String creator;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @SerializedName("items")
    @Expose
    @TypeConverters(DataTypeConverter.class)
    private List<ItemModel> items = null;
    @SerializedName("name")
    @Expose
    private String name;

    public MuseModel(String name){
        this.name = name;

    }
    protected  MuseModel(@NonNull Parcel in) {
        uId = in.readInt();
        id = in.readInt();
        name = in.readString();
        items = in.readArrayList(ItemModel.class.getClassLoader());
        dateCreated = in.readString();
        creator = in.readString();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(@NonNull String creator) {
        this.creator = creator;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(@NonNull String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }
    public Integer getUId() {
        return uId;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(@NonNull List<ItemModel> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeInt(uId);
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

