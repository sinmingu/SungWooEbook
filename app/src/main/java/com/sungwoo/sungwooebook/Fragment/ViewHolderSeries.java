package com.sungwoo.sungwooebook.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sungwoo.sungwooebook.Model.DataModel;
import com.sungwoo.sungwooebook.R;

public class ViewHolderSeries extends RecyclerView.ViewHolder {
    TextView mTxtSeries;
    ImageView mImgSeries;
    public ViewHolderSeries(@NonNull View itemView) {
        super(itemView);

        mImgSeries = itemView.findViewById(R.id.imgSeries);
        mTxtSeries = itemView.findViewById(R.id.txtSeries);
    }
    public void onBind(DataModel data){
        mTxtSeries.setText(data.getTitle());
        mImgSeries.setImageResource(data.getImage());
    }
}
