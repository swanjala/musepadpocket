package googlecodechallenge.sam.musepadpocket.view.userViews;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.view.fragments.UserFragment;

public class SignInActivity extends AppCompatActivity {


    private FragmentManager fragmentManager =
            getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_login);

        if (onSavedInstanceState == null){
            loadView();
        }

        if (onSavedInstanceState != null){

            SharedPreferences preferences = getSharedPreferences("token",0);
            preferences.edit().remove("token").commit();
        }

        StrictMode.ThreadPolicy policy =new StrictMode
                .ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

    }

    public void loadView(){
        if(fragmentManager.getBackStackEntryCount() == 0){
            UserFragment loginFragment = new UserFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.loginContainer, loginFragment)
                    .commitAllowingStateLoss();
        }
    }


}
