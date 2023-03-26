package com.sungwoo.sungwooebook.Fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sungwoo.sungwooebook.ContentActivity;
import com.sungwoo.sungwooebook.Model.ContentModel;
import com.sungwoo.sungwooebook.Model.DataModel;
import com.sungwoo.sungwooebook.R;
import com.sungwoo.sungwooebook.Utils.UtilsLog;

public class ViewHolderContents extends RecyclerView.ViewHolder {
    TextView mTxtContent;
    ImageView mImgContent;

    public ViewHolderContents(@NonNull View itemView) {
        super(itemView);

        mImgContent = itemView.findViewById(R.id.imgContent);
        mTxtContent = itemView.findViewById(R.id.txtContent);
    }
    public void onBind(ContentModel data, Context context){
        mTxtContent.setText(data.getTitle());

        Glide.with(mImgContent).load(data.getImageUrl()).into(mImgContent);

        // 23-03-22 해당 라인은 임시 테스트
        mTxtContent.setLines(2);

        mImgContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("imageUri", data.getImageUrl());
                intent.putExtra("pdfUri", data.getPdfUrl());
                context.startActivity(intent);
            }
        });
    }
}
