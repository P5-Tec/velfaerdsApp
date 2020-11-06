package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.VideoView;

public class introPage extends AppCompatActivity
{
    private TextView showText;
    private EditText enterName;
    private EditText enterJob;
    private VideoView videoView;


    String selectedItem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);

        // instantiating
        enterName = findViewById(R.id.editTextName);
        enterJob = findViewById(R.id.editTextJob);
         videoView = findViewById(R.id.videoView);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);


                // Calling the method to create the spinnerdropdown
        createSpinnerDropdown();


    }

    // creating and populating the dropdown
    private void createSpinnerDropdown() {

        Spinner spinner = (Spinner) findViewById(R.id.spinnerGender);

        List<String> list = new ArrayList<>();
        list.add("Vælg køn");
        list.add("Mand");
        list.add("Kvinde");
        list.add("Andet");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter to the spinner
        spinner.setAdapter(dataAdapter);

        // Attaching the listener to the spinner
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedItem = parent.getItemAtPosition(position).toString();
            if(!selectedItem.equals("Vælg køn"))
            {
                Toast.makeText(parent.getContext(), "Køn valgt: " + selectedItem, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


    public void forward(View view) {
        Intent intent = new Intent(this, customAvatar.class);

        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}