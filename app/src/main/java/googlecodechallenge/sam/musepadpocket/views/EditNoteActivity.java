package googlecodechallenge.sam.musepadpocket.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.networkutils.ApiCalls;
import googlecodechallenge.sam.musepadpocket.networkutils.BuildUrls;

/**
 * Created by sam on 7/1/18.
 */

public class EditNoteActivity extends AppCompatActivity {

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

        et_my_note_entry.setText( extras.getString("Description"));
        this.museId = extras.getString("MuseId");
        this.noteId = extras.getString("NoteId");


        bt_save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedText = et_my_note_entry.getText().toString();
                saveNote(editedText, editedText.substring(0, editedText.length()/2));
            }
        });


    }

    private void saveNote(String entry, String item_name) {
        ApiCalls apiCalls = new ApiCalls();
        Boolean saveNote = apiCalls.editNote(initUrlBuilder(), entry, item_name,this);
        if (saveNote) {
            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Note not saved try again", Toast.LENGTH_LONG).show();
        }

    }

    private URL initUrlBuilder() {
        BuildUrls buildUrls = new BuildUrls(this);
        URL url = buildUrls.buildUrlForItemActions(museId,noteId);
        Log.d("data goes",String.valueOf(url));

        return url;
    }

}
