package com.bkacad.nnt.sharepreferencedemod01k11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnRandom;
    private TextView tvNumber;
    private int nb = 0;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private void initUI(){
        btnRandom = findViewById(R.id.btn_main_random);
        tvNumber =  findViewById(R.id.tv_main_number);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        mPreferences = this.getPreferences(MODE_PRIVATE);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Random số -> cập nhật vào textview
                nb = Utils.getRandomNumber(1, 100);
                tvNumber.setText(String.valueOf(nb));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int lastNumber = mPreferences.getInt("nb",0);
        tvNumber.setText(String.valueOf(lastNumber));
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Lưu dữ liệu vào
        mEditor = mPreferences.edit();
        mEditor.putInt("nb",nb);
        mEditor.apply();

    }
}