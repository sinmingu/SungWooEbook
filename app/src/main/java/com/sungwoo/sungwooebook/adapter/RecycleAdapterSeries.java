package com.sungwoo.sungwooebook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sungwoo.sungwooebook.Fragment.ViewHolderSeries;
import com.sungwoo.sungwooebook.Model.ContentModel;
import com.sungwoo.sungwooebook.Model.DataModel;
import com.sungwoo.sungwooebook.R;

import java.util.ArrayList;

public class RecycleAdapterSeries extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<ContentModel> listData = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_series, parent, false);
        return new ViewHolderSeries(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderSeries)holder).onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(ContentModel data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }
}
