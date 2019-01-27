package googlecodechallenge.sam.musepadpocket.view.userViews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.datamodel.api.ApiCalls;
import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.IApiCalls;
import googlecodechallenge.sam.musepadpocket.model.UserModel;

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

    private String userName = "",
            password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        signup.setOnClickListener(this);

        if (savedInstanceState != null){

            SharedPreferences preferences = getSharedPreferences("token",0);
            preferences.edit().remove("token").commit();
        }

        StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @OnClick(R.id.bt_login)
    public void onClick(View v){
        int viewId = v.getId();
        if (viewId ==R.id.bt_signup){
            showSighnUpDialog(this);

        }else if (viewId ==R.id.bt_login){

            this.userName = et_user_name_input.getText().toString();
            this.password = et_password_input.getText().toString();

            UserModel userModel = new UserModel(userName,password);
            IApiCalls apiCalls = new ApiCalls(userModel,this);

            apiCalls.login();

        }
    }

    protected void showSighnUpDialog(final Context context) {
        View promptView = LayoutInflater
                .from(context)
                .inflate(R.layout.signup_dialog, null);
        final EditText et_userName, et_password, et_email;

        et_userName = promptView.findViewById(R.id.et_signup_dialog_user_name_);
        et_password = promptView.findViewById(R.id.et_signup_dialog_password);
        et_email = promptView.findViewById(R.id.et_dialog_email);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Sign Me Up",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UserModel userModel = new UserModel(et_userName.getText().toString(),
                                        et_password.getText().toString(),et_email.getText().toString());
                                Toast.makeText(context, "Signed up", Toast.LENGTH_LONG).show();
                              IApiCalls apiCalls = new ApiCalls(userModel,context);
                              apiCalls.register();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }


}
