package googlecodechallenge.sam.musepadpocket.model;

public class UserRequestModel {

    String username, password,email;
    public UserRequestModel(String username, String password){
        this.username = username;
        this.password = password;

    }
    public UserRequestModel(String username,String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
