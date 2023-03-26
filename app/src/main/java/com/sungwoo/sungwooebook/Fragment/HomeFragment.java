package com.sungwoo.sungwooebook.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.sungwoo.sungwooebook.Model.ContentModel;
import com.sungwoo.sungwooebook.Model.DataModel;
import com.sungwoo.sungwooebook.R;
import com.sungwoo.sungwooebook.Utils.DBKey;
import com.sungwoo.sungwooebook.Utils.UtilsLog;
import com.sungwoo.sungwooebook.adapter.RecycleAdapterContents;
import com.sungwoo.sungwooebook.adapter.RecycleAdapterSeries;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Context mContext = null;
    View mMyView = null;
    RecycleAdapterSeries adapterSeries;
    RecycleAdapterContents adapterContents;

    private int index = 0;
    private ArrayList<DataModel> dataModels = new ArrayList<DataModel>();
    private DatabaseReference contentDB;
    private FirebaseStorage storage;

    public HomeFragment(Context context){
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMyView = inflater.inflate(R.layout.activity_home_content, container, false);
        return mMyView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

        /*
        23.03.25 smg :: 기존 Data불러오는 메소드에 대한 미사용처리
        getData();
        getStoragePhotoData();
        getStoragePdfData();
        */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        contentDB.removeEventListener(childEventListener);
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

        adapterContents = new RecycleAdapterContents(getContext());
        recyclerViewContents.setAdapter(adapterContents);

        contentDB = FirebaseDatabase.getInstance().getReference(DBKey.DB_CONTENTS);
        contentDB.addChildEventListener(childEventListener);
        storage = FirebaseStorage.getInstance();

        //uploadContent("", "", "");
    }

    private void getData(){
        //23-03-22 테스트용으로 adapterContents는 임시 주석
        /*
        DataModel data = new DataModel(R.drawable.iron_man, "아이언맨");
        adapterSeries.addItem(data);
        //adapterContents.addItem(data);
        data = new DataModel(R.drawable.spider_man, "스파이더맨");
        adapterSeries.addItem(data);
        //adapterContents.addItem(data);
        data = new DataModel(R.drawable.black_panther, "블랙팬서");
        adapterSeries.addItem(data);
        //adapterContents.addItem(data);
        data = new DataModel(R.drawable.doctor, "닥터스트레인지");
        adapterSeries.addItem(data);
        //adapterContents.addItem(data);
        data = new DataModel(R.drawable.hulk, "헐크");
        adapterSeries.addItem(data);
        //adapterContents.addItem(data);
        data = new DataModel(R.drawable.thor, "토르");
        adapterSeries.addItem(data);
        //adapterContents.addItem(data);

        DataModel data2 = new DataModel(R.drawable.iron_man, "아이언맨");
        adapterSeries.addItem(data2);
        //adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.spider_man, "스파이더맨");
        adapterSeries.addItem(data2);
        //adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.black_panther, "블랙팬서");
        adapterSeries.addItem(data2);
        //adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.doctor, "닥터스트레인지");
        adapterSeries.addItem(data2);
        //adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.hulk, "헐크");
        adapterSeries.addItem(data2);
        //adapterContents.addItem(data2);
        data2 = new DataModel(R.drawable.thor, "토르");
        adapterSeries.addItem(data2);
        //adapterContents.addItem(data2);

        */
    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
            ContentModel contentModel = snapshot.getValue(ContentModel.class);

            if(contentModel == null) return;

            UtilsLog.d("smg childEventListener, title : " + contentModel.getTitle());
            adapterContents.addItem(contentModel);
            adapterSeries.addItem(contentModel);
            //contentModels.add(contentModel);
            adapterContents.notifyDataSetChanged();
            adapterSeries.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

        }

        @Override
        public void onChildRemoved(DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

        }

        @Override
        public void onCancelled(DatabaseError error) {

        }
    };

    private void getStoragePhotoData() {
        StorageReference photoRef = storage.getReference().child("sungwoo/photo");
        photoRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {

                for (StorageReference item : listResult.getItems()) {
                    item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }

                getStoragePdfData();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void getStoragePdfData() {
        StorageReference photoRef = storage.getReference().child("sungwoo/pdf");
        photoRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference item : listResult.getItems()) {

                    item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void uploadContent(String title, String imageUrl, String pdfUrl) {
        ContentModel contentModel = new ContentModel(title, imageUrl, pdfUrl);
        contentDB.push().setValue(contentModel);
    }
}
