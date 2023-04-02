package com.sungwoo.sungwooebook.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sungwoo.sungwooebook.MainActivity;
import com.sungwoo.sungwooebook.Model.ContentModel;
import com.sungwoo.sungwooebook.Model.UserModel;
import com.sungwoo.sungwooebook.R;
import com.sungwoo.sungwooebook.Utils.DBKey;
import com.sungwoo.sungwooebook.Utils.UtilsLog;

public class MyPageFragment extends Fragment {

    private Context mContext;
    private TextView mMypageId, mMypageName, mUserLogout;
    View mMyView = null;

    private FirebaseAuth auto;
    private DatabaseReference userDB;

    public MyPageFragment(Context context){
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMyView = inflater.inflate(R.layout.activity_mypage, container, false);
        return mMyView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void init() {
        mMypageId = (TextView) mMyView.findViewById(R.id.mypage_id);
        mMypageName = (TextView) mMyView.findViewById(R.id.mypage_name);
        mUserLogout = (TextView) mMyView.findViewById(R.id.userLogout);

        auto = FirebaseAuth.getInstance();
        userDB = FirebaseDatabase.getInstance().getReference(DBKey.DB_USERS);

        userDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snap: snapshot.getChildren()) {
                    String key = snap.getKey();
                    UserModel userModel = snap.getValue(UserModel.class);
                    if(userModel.getUid().equals(auto.getCurrentUser().getUid())) {
                        mMypageId.setText(userModel.getId());
                        mMypageName.setText(userModel.getName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mUserLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mContext == null) return;

                auto.signOut();

                if(auto.getCurrentUser() == null) {
                    Toast.makeText(mContext, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}
