package googlecodechallenge.sam.musepadpocket.model;

import android.support.annotation.NonNull;

public class UserRequestModel {

    String username, password,email;

    public UserRequestModel(@NonNull UserModel userModel){

        this.username = userModel.getUserName();
        this.password = userModel.getPassword();
        this.email = userModel.getEmail();

    }

}
