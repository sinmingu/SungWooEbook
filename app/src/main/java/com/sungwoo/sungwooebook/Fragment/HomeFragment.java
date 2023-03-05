package com.sungwoo.sungwooebook.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sungwoo.sungwooebook.Model.DataModel;
import com.sungwoo.sungwooebook.R;
import com.sungwoo.sungwooebook.adapter.RecycleAdapterContents;
import com.sungwoo.sungwooebook.adapter.RecycleAdapterSeries;

public class HomeFragment extends Fragment {

    Context mContext = null;
    View mMyView = null;
    RecycleAdapterSeries adapterSeries;
    RecycleAdapterContents adapterContents;


    public HomeFragment(Context context){
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMyView = inflater.inflate(R.layout.activity_bottom, container, false);
        init();
        getData();

        return mMyView;
    }

    private void init(){
        RecyclerView recyclerViewSeries = mMyView.findViewById(R.id.recyclerViewSeries);
        RecyclerView recyclerViewContents = mMyView.findViewById(R.id.recyclerViewContent);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewSeries.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        recyclerViewContents.setLayoutManager(gridLayoutManager);

        adapterSeries = new RecycleAdapterSeries();
        recyclerViewSeries.setAdapter(adapterSeries);

        adapterContents = new RecycleAdapterContents();
        recyclerViewContents.setAdapter(adapterContents);

    }

    private void getData(){
        DataModel data = new DataModel(R.drawable.iron_man, "아이언맨");
        adapterSeries.addItem(data);
        adapterContents.addItem(data);
        data = new DataModel(R.drawable.spider_man, "스파이더맨");
        adapterSeries.addItem(data);
        adapterContents.addItem(data);
        data = new DataModel(R.drawable.black_panther, "블랙팬서");
        adapterSeries.addItem(data);
        adapterContents.addItem(data);
        data = new DataModel(R.drawable.doctor, "닥터스트레인지");
        adapterSeries.addItem(data);
        adapterContents.addItem(data);
        data = new DataModel(R.drawable.hulk, "헐크");
        adapterSeries.addItem(data);
        adapterContents.addItem(data);
        data = new DataModel(R.drawable.thor, "토르");
        adapterSeries.addItem(data);
        adapterContents.addItem(data);

        DataModel data2 = new DataModel(R.drawable.iron_man, "아이언맨");
        adapterSeries.addItem(data2);
        adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.spider_man, "스파이더맨");
        adapterSeries.addItem(data2);
        adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.black_panther, "블랙팬서");
        adapterSeries.addItem(data2);
        adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.doctor, "닥터스트레인지");
        adapterSeries.addItem(data2);
        adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.hulk, "헐크");
        adapterSeries.addItem(data2);
        adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.thor, "토르");
        adapterSeries.addItem(data2);
        adapterContents.addItem(data2);
    }

}
