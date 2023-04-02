package com.sungwoo.sungwooebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.sungwoo.sungwooebook.Utils.UtilsLog;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class ContentActivity extends AppCompatActivity {

    private Button mPreviewBtn;
    private ImageView mPreviewImage;
    private String imageUri, pdfUri;

    private RemotePDFViewPager remotePDFViewPager;
    private PDFPagerAdapter pdfAdapter;
    private LinearLayout pdfLayout;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        imageUri = intent.getStringExtra("imageUri");
        pdfUri = intent.getStringExtra("pdfUri");

        initPreviewWidget();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pdfAdapter != null) {
            pdfAdapter.close();
        }
    }

    private void initPreviewWidget() {
        mPreviewBtn = (Button) findViewById(R.id.preview_btn);
        mPreviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remotePDFViewPager = new RemotePDFViewPager(getApplicationContext(), pdfUri, downFileListener);
                loadingPDFWork();
            }
        });

        mPreviewImage = (ImageView) findViewById(R.id.preview_image);
        Glide.with(mPreviewImage).load(imageUri).into(mPreviewImage);

        pdfLayout = (LinearLayout) findViewById(R.id.pdf_layout);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private DownloadFile.Listener downFileListener = new DownloadFile.Listener() {
        @Override
        public void onSuccess(String url, String destinationPath) {
            pdfAdapter = new PDFPagerAdapter(getApplicationContext(), FileUtil.extractFileNameFromURL(url));
            remotePDFViewPager.setAdapter(pdfAdapter);
            updateLayout();
            endPDFWork();
        }

        @Override
        public void onFailure(Exception e) {
            endPDFWork();
        }

        @Override
        public void onProgressUpdate(int progress, int total) {
            UtilsLog.d("progress : " + progress + ", total : " + total);
        }
    };

    private void updateLayout() {
        pdfLayout.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    private void androidPdfViewer() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(pdfUri),"application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.getStackTrace();
        }
    }

    private void loadingPDFWork() {
        if(pdfLayout != null) {
            pdfLayout.setVisibility(View.GONE);
        }

        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    private void endPDFWork() {
        if(pdfLayout != null) {
            pdfLayout.setVisibility(View.VISIBLE);
        }

        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

}