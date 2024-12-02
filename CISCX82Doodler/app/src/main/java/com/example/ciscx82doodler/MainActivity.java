// Step 3: Main Activity - MainActivity.java
package com.example.ciscx82doodler;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.ciscx82doodler.R;

public class MainActivity extends Activity {
    private DoodleView doodleView;
    private SeekBar brushSize;
    private SeekBar opacityControl;
    private LinearLayout colorPalette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doodleView = findViewById(R.id.doodleView);
        brushSize = findViewById(R.id.brush_size);
        opacityControl = findViewById(R.id.opacity_control);
        colorPalette = findViewById(R.id.color_palette);

        // Set default brush size
        brushSize.setProgress(20); // Set initial progress to match default size

        Button brushButton = findViewById(R.id.brush_button);
        brushButton.setOnClickListener(v -> {
            if (brushSize.getVisibility() == View.GONE) {
                brushSize.setVisibility(View.VISIBLE);
            } else {
                brushSize.setVisibility(View.GONE);
            }
        });

        brushSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scaledBrushSize = progress * 2;  // Multiply by 2 for increased effect
                doodleView.setBrushSize(scaledBrushSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        Button opacityButton = findViewById(R.id.opacity_button);
        opacityButton.setOnClickListener(v -> {
            if (opacityControl.getVisibility() == View.GONE) {
                opacityControl.setVisibility(View.VISIBLE);
            } else {
                opacityControl.setVisibility(View.GONE);
            }
        });

        opacityControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.setOpacity(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        Button colorButton = findViewById(R.id.color_button);
        colorButton.setOnClickListener(v -> {
            if (colorPalette.getVisibility() == View.GONE) {
                colorPalette.setVisibility(View.VISIBLE);
            } else {
                colorPalette.setVisibility(View.GONE);
            }
        });

        Button colorViolet = findViewById(R.id.color_violet);
        colorViolet.setOnClickListener(v -> doodleView.setBrushColor(Color.parseColor("#8B00FF")));

        Button colorIndigo = findViewById(R.id.color_indigo);
        colorIndigo.setOnClickListener(v -> doodleView.setBrushColor(Color.parseColor("#4B0082")));

        Button colorBlue = findViewById(R.id.color_blue);
        colorBlue.setOnClickListener(v -> doodleView.setBrushColor(Color.BLUE));

        Button colorGreen = findViewById(R.id.color_green);
        colorGreen.setOnClickListener(v -> doodleView.setBrushColor(Color.GREEN));

        Button colorYellow = findViewById(R.id.color_yellow);
        colorYellow.setOnClickListener(v -> doodleView.setBrushColor(Color.parseColor("#FFFF00")));

        Button colorOrange = findViewById(R.id.color_orange);
        colorOrange.setOnClickListener(v -> doodleView.setBrushColor(Color.parseColor("#FF7F00")));

        Button colorRed = findViewById(R.id.color_red);
        colorRed.setOnClickListener(v -> doodleView.setBrushColor(Color.RED));

        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(v -> doodleView.clearCanvas());
    }
}
