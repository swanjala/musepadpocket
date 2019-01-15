package googlecodechallenge.sam.musepadpocket.models;


import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

public class ItemModel {

    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private Object dateModified;
    @SerializedName("done")
    @Expose
    private Boolean done;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("item_description")
    @Expose
    private String itemDescription;
    @SerializedName("muse_id")
    @Expose
    private Integer museId;
    @SerializedName("name")
    @Expose
    private String name;

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getDateModified() {
        return dateModified;
    }

    public void setDateModified(Object dateModified) {
        this.dateModified = dateModified;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getMuseId() {
        return museId;
    }

    public void setMuseId(Integer museId) {
        this.museId = museId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
