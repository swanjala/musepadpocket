package googlecodechallenge.sam.musepadpocket.model;

/**
 * Created by sam on 7/1/18.
 */

public class UserModel {

    private String userName,password, email,token, message;

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
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
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
    public void setMessage(String responseMessage){
        this.message = responseMessage;
    }
    public String getMessage(){
        return message;
    }
}
