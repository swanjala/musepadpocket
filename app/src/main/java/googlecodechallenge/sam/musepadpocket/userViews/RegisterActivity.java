package googlecodechallenge.sam.musepadpocket.userViews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.networkutils.ApiCalls;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;

/**
 * Class lets the user sign up , added to a remote database
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bt_register)
    Button bt_register;
    @BindView(R.id.et_name)
    EditText et_user_name;
    @BindView(R.id.et_password)
    EditText et_register_password;
    @BindView(R.id.et_email)
    EditText et_register_email;

    @NonNull
    private String
            username = "",
            email = "",
            password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.bt_register)
    public void onClick(View v){
        int viewId = v.getId();
        if(viewId ==R.id.bt_register){
            this.username = et_user_name.getText().toString();
            this.email = et_register_email.getText().toString();
            this.password = et_register_password.getText().toString();

            signUp();
        }
    }


    public void signUp(){
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForUserSignUp();

        ApiCalls apiCalls = new ApiCalls(url,username,password,email);
        boolean successRegister = apiCalls.registerUser();
        if (successRegister){
            Toast.makeText(this, R.string.success_registration,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, R.string.unable_to_register,Toast.LENGTH_SHORT).show();

        }
    }
}
