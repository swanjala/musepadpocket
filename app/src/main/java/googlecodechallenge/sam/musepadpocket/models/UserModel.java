package googlecodechallenge.sam.musepadpocket.models;

/**
 * Created by sam on 7/1/18.
 */

public class UserModel {

    private String userName,password, email;

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
