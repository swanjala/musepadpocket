package googlecodechallenge.sam.musepadpocket.userViews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.networkutils.ApiCalls;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;
import googlecodechallenge.sam.musepadpocket.museViews.MuseListActivityFree;

/*
* Class lets the user sign into the application
* */

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bt_signup)
    Button signup;
    @BindView(R.id.bt_login)
    Button bt_login_user;
    @BindView(R.id.et_user_name)
    EditText et_user_name_input;
    @BindView(R.id.et_password)
    EditText et_password_input;

    @NonNull
    private String userName = "",
            password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        signup.setOnClickListener(this);

        StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    public void onClick(View v){
        int viewId = v.getId();
        if (viewId ==R.id.bt_signup){

            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        }else if (viewId ==R.id.bt_login){

            this.userName = et_user_name_input.getText().toString();
            this.password = et_password_input.getText().toString();

            signIn();

        }
    }

    public void signIn(){
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForUserLogin();
        Log.d("Url",String.valueOf(url));

        ApiCalls apiCalls = new ApiCalls();

        String successToken= apiCalls.login(url,userName, password);

        if (successToken.equals("")){
            Toast.makeText(this, R.string.unable_to_login,Toast.LENGTH_LONG).show();

        }else {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            sp.edit().putString("token", successToken).apply();
            Intent intent = new Intent(this, MuseListActivityFree.class);
            intent.putExtra("Token",successToken);


            startActivity(intent);

        }
    }

}
