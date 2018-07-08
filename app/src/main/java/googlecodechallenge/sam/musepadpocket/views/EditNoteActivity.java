package googlecodechallenge.sam.musepadpocket.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
 * Edit note allows tha user to edit and already existing note
 */

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_save_note;
    EditText et_my_note_entry;
    private String museId;
    private String noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note_layout);

        et_my_note_entry = findViewById(R.id.et_notes_input);
        bt_save_note = findViewById(R.id.bt_save_note);


        final Bundle extras = getIntent().getExtras();

        et_my_note_entry.setText(extras.getString("Description"));
        this.museId = extras.getString(getString(R.string.muse_id));
        this.noteId = extras.getString(getString(R.string.note_id));


        bt_save_note.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.bt_save_note) {
            String editedText = et_my_note_entry.getText().toString();
            editCurrentNote(editedText, editedText.substring(0, editedText.length() / 2), this);
        }

    }

    private void editCurrentNote(String entry, String item_name, Context context) {

        Context appContext = context;
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        editNoteNetworkCall(entry, item_name, appContext);

        boolean saveNote = sharedPreferences.getBoolean("EditNote", false);

        if (saveNote) {
            Toast.makeText(this, R.string.note_saved_successfully, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.note_note_saved, Toast.LENGTH_LONG).show();
        }

    }

    private void editNoteNetworkCall(final String entry, final String item_name, final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ApiCalls apiCalls = new ApiCalls();
                apiCalls.editNote(initUrlBuilder(), entry, item_name, context);
            }
        }).start();
    }

    private URL initUrlBuilder() {
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForItemActions(museId, noteId);
        return url;
    }

}
