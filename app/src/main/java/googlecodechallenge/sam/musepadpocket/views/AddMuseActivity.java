package googlecodechallenge.sam.musepadpocket.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.networkutils.ApiCalls;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;

/**
 * Class lets the user add a new muse activity to the application.
 */

public class AddMuseActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_add_new_muse_title, et_add_new_muse_description;
    Button bt_add_new_muse_button;

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_muse_layout);
    intiUi();

}

private void intiUi(){
    et_add_new_muse_title = findViewById(R.id.et_input_muse_title);
    et_add_new_muse_description = findViewById(R.id.et_muse_description);
        bt_add_new_muse_button = findViewById(R.id.bt_add_new_muse);
        bt_add_new_muse_button.setOnClickListener(this);
}

    private URL initUrlBuilder() {
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForMuseActions();

        return url;
    }
@Override
    public void onClick(View view){

    ApiCalls apiCalls = new ApiCalls();
    URL url = initUrlBuilder();
    boolean addNewMuse = apiCalls.addMuse(url,et_add_new_muse_title.getText().toString(),
            et_add_new_muse_description.getText().toString(),this);

    if (addNewMuse){
        Toast.makeText(this, R.string.muse_added_successfully,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,MuseListActivity.class);
        startActivity(intent);
    }else {
        Toast.makeText(this, R.string.muse_not_added_successfully, Toast.LENGTH_LONG).show();
    }


    }
}
