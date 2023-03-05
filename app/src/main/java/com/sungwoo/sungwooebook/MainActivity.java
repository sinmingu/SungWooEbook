package com.sungwoo.sungwooebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sungwoo.sungwooebook.Utils.UtilsLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilsLog.d("MainActivity onCreate");

        Intent intent = new Intent(this, BottomAcitivity.class);
        startActivity(intent);
        finish();

        //setContentView(R.layout.activity_main);
    }
}