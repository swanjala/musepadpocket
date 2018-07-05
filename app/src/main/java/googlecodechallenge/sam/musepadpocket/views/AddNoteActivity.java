package googlecodechallenge.sam.musepadpocket.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.networkutils.ApiCalls;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;

/**
 * Class lets the user add new notes to their muse list
 */

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_addNote;
    EditText et_note_entry, et_note_title_entry;
    private Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note_layout);
        initUi();
        this.extras = getIntent().getExtras();

    }
    private void initUi(){
        et_note_entry = findViewById(R.id.et_notes_input);
        bt_addNote = findViewById(R.id.bt_save_note);
        bt_addNote.setOnClickListener(this);
        et_note_title_entry = findViewById(R.id.et_note_title);

    }
    @Override
    public void onClick(View view){
        int viewId = view.getId();

        if(viewId == R.id.bt_save_note){
           saveNote();
        }
    }

    private void saveNote(){

        ApiCalls apiCalls = new ApiCalls();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = "Bearer ".concat(sharedPreferences.getString("token",""));
        boolean addNoteState = apiCalls.addNote(initUrlBuilder(),et_note_entry.getText().toString(),
                et_note_title_entry.getText().toString(),token);
        if(addNoteState){
            Intent intent = new Intent(this, MuseListActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, R.string.note_not_added,Toast.LENGTH_LONG).show();
        }
    }

    private URL initUrlBuilder() {
        String museId = extras.getString("Id");
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForItemActions(museId);

        return url;
    }
}
