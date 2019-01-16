package googlecodechallenge.sam.musepadpocket.models;

/**
 * Created by sam on 7/1/18.
 */

public class UserModel {

    private String userName,password, email;

    public UserModel(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public UserModel(String userName, String password, String email){
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public UserModel() {}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
