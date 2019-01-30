package googlecodechallenge.sam.musepadpocket.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sam on 7/1/18.
 */
@Entity(tableName = "users")
public class UserModel {

    @Expose
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @Expose
    @SerializedName("user")
    private String userName;

    @Expose
    @SerializedName("password")

    private String password;

    @Expose
    @SerializedName("email")
    private String email;

    private String token;

    private String message;

    public UserModel(@NonNull String userName,
                     @NonNull String password){
        this.userName = userName;
        this.password = password;
    }
    public UserModel(@NonNull String userName,
                     @NonNull String password,
                     @NonNull String email){
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public UserModel() {}

    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }

    public String getEmail() {
        return email;
    }
    public void setId(Integer id){
        this.id =id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password){
        this.password = password;

    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
    public void setMessage(String responseMessage){
        this.message = responseMessage;
    }
    public String getMessage(){
        return message;
    }
}
