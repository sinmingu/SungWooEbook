package com.sungwoo.sungwooebook.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sungwoo.sungwooebook.Model.DataModel;
import com.sungwoo.sungwooebook.R;

public class ViewHolderContents extends RecyclerView.ViewHolder {
    TextView mTxtContent;
    ImageView mImgContent;

    public ViewHolderContents(@NonNull View itemView) {
        super(itemView);

        mImgContent = itemView.findViewById(R.id.imgContent);
        mTxtContent = itemView.findViewById(R.id.txtContent);
    }
    public void onBind(DataModel data){
        mTxtContent.setText(data.getTitle());
        mImgContent.setImageResource(data.getImage());
    }
}
