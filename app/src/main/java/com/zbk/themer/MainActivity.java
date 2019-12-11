package com.zbk.themer;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private EditText editName;
    private FrameLayout colorBox;
    private SeekBar seekR;
    private SeekBar seekB;
    private SeekBar seekG;
    private SharedPreferences pref;
    int red = 0;
    int blue = 0;
    int green = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnLoad = findViewById(R.id.btnLoad);

        editName = findViewById(R.id.editName);
        colorBox = findViewById(R.id.colorbox);

        seekR = findViewById(R.id.seekR);
        seekB = findViewById(R.id.seekB);
        seekG = findViewById(R.id.seekG);

        // object of shared preferences
        pref = getSharedPreferences("settings", MODE_PRIVATE);

        seekR.setOnSeekBarChangeListener(this);
        seekB.setOnSeekBarChangeListener(this);
        seekG.setOnSeekBarChangeListener(this);

    }

    public void saveSettings(View v) {
        String name = editName.getText().toString();
        int b = seekB.getProgress();
        int r = seekR.getProgress();
        int g = seekG.getProgress();

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("theme_name", name);
        editor.putInt("red", r);
        editor.putInt("green", g);
        editor.putInt("blue", b);
        editor.apply();
        Log.d("APP_STATUS","saved");
        Toast.makeText(this, "saved settings" , Toast.LENGTH_SHORT).show();
    }

    public void loadSettings(View v) {
        String name = pref.getString("theme_name", "Not found");
        int r = pref.getInt("red",0);
        int b = pref.getInt("blue",0);
        int g = pref.getInt("green",0);

        // load setting on screen
        editName.setText(name);
        seekB.setProgress(b);
        seekR.setProgress(r);
        seekG.setProgress(g);
        colorBox.setBackgroundColor(Color.rgb(r,g,b));
        Toast.makeText(this, "loaded theme", Toast.LENGTH_SHORT).show();
    }

    public void deleteSettings(View v) {
        pref.edit().clear().apply();
        Toast.makeText(this, "theme delete", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekR :
                red = progress;
                break;
            case R.id.seekB:
                blue = progress;
                break;
            case R.id.seekG:
                green = progress;
                break;
        }
        colorBox.setBackgroundColor(Color.rgb(red,green,blue));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // no work
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // no work
    }
}
