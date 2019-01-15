package googlecodechallenge.sam.musepadpocket.models;

import java.util.List;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

public class MuseModel  {

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
    private List<ItemModel> items = null;
    @SerializedName("name")
    @Expose
    private String name;

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

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

