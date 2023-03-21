package com.sungwoo.sungwooebook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sungwoo.sungwooebook.Fragment.ViewHolderContents;
import com.sungwoo.sungwooebook.Model.ContentData;
import com.sungwoo.sungwooebook.Model.DataModel;
import com.sungwoo.sungwooebook.R;

import java.util.ArrayList;

public class RecycleAdapterContents extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<DataModel> listData = new ArrayList<>();
    private Context mContext = null;

    public RecycleAdapterContents(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contents, parent, false);
        return new ViewHolderContents(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderContents)holder).onBind(listData.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(DataModel data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }
}
