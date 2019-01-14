package googlecodechallenge.sam.musepadpocket.museViews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Class lets the user add new notes to their muse list
 */

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_notes_input)
    EditText et_note_entry;

    @BindView(R.id.et_note_title)
    EditText et_note_title_entry;

    @BindView(R.id.bt_save_note)
    Button bt_addNote;

    private Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note_layout);
        ButterKnife.bind(this);
        bt_addNote.setOnClickListener(this);

        this.extras = getIntent().getExtras();

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.bt_save_note) {
            saveNote();
        }
    }

    private void saveNote() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = "Bearer ".concat(sharedPreferences.getString("token", ""));

        saveNoteNetworkCall(token, this);

        boolean addNoteState = sharedPreferences.getBoolean("AddNote", false);

        if (addNoteState) {
            Intent intent = new Intent(this, MuseListActivityFree.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.note_not_added, Toast.LENGTH_LONG).show();
        }
    }

    private void saveNoteNetworkCall(String token, Context appContext) {

        final Context context = appContext;
        final String accessToken = token;

        new Thread(new Runnable() {
            @Override
            public void run() {
                ApiCalls apiCalls = new ApiCalls();
                apiCalls.addNote(initUrlBuilder(), et_note_entry.getText().toString(),
                        et_note_title_entry.getText().toString(), accessToken, context);
            }
        }).start();

    }

    private URL initUrlBuilder() {
        String museId = extras.getString("Id");
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForItemActions(museId);

        return url;
    }
}
