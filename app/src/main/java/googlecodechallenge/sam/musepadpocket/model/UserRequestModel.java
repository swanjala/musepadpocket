package googlecodechallenge.sam.musepadpocket.model;

public class UserRequestModel {

    String username, password,email;
    public UserRequestModel(UserModel userModel){
        this.username = userModel.getUserName();
        this.password = userModel.getPassword();
        this.email = userModel.getEmail();
    }

}
