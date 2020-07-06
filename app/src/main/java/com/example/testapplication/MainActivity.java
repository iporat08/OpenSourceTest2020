package com.example.testapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aditya.filebrowser.Constants;
import com.aditya.filebrowser.FileBrowser;
import com.aditya.filebrowser.FileChooser;

public class MainActivity extends AppCompatActivity {

    public static final int PICK_FILE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.testButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(), FileChooser.class);
                i2.putExtra(Constants.SELECTION_MODE, Constants.SELECTION_MODES.SINGLE_SELECTION.ordinal());
                startActivityForResult(i2, PICK_FILE_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && data!=null) {
            if (resultCode == RESULT_OK) {
                Uri file = data.getData();
                String path = file.toString();
                Uri uri = Uri.parse(path.substring(7));
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("application/pdf");
                share.putExtra(Intent.EXTRA_STREAM, uri);
                share.setPackage("com.whatsapp");
                startActivity(share);

            }
        }
    }
}