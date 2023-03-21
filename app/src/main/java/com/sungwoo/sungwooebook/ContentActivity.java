package com.sungwoo.sungwooebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ContentActivity extends AppCompatActivity {

    private Button mPreviewBtn;
    private ImageView mPreviewImage;
    private String pdfUri;
    private int imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        imageUri = intent.getIntExtra("image", R.drawable.hulk);
        pdfUri = intent.getStringExtra("pdfUri");

        initPreviewWidget();
    }

    private void initPreviewWidget() {
        mPreviewBtn = (Button) findViewById(R.id.preview_btn);
        mPreviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(pdfUri),"application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.getStackTrace();
                }
            }
        });

        mPreviewImage = (ImageView) findViewById(R.id.preview_image);
        mPreviewImage.setImageResource(imageUri);

    }
}