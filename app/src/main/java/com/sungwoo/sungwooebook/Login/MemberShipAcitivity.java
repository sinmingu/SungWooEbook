package com.sungwoo.sungwooebook.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sungwoo.sungwooebook.MainActivity;
import com.sungwoo.sungwooebook.Model.ContentModel;
import com.sungwoo.sungwooebook.Model.UserModel;
import com.sungwoo.sungwooebook.R;
import com.sungwoo.sungwooebook.Utils.DBKey;

public class MemberShipAcitivity extends AppCompatActivity {

    private EditText mEditId, mEditName, mEditPass;
    private TextView mSignup;
    private FirebaseAuth auto;
    private DatabaseReference userDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        init();
    }

    private void init() {
        mEditId = (EditText) findViewById(R.id.edit_id);
        mEditName = (EditText) findViewById(R.id.edit_name);
        mEditPass = (EditText) findViewById(R.id.edit_pw);
        mSignup = (TextView) findViewById(R.id.signup);

        auto = FirebaseAuth.getInstance();
        userDB = FirebaseDatabase.getInstance().getReference(DBKey.DB_USERS);

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editInputCheck()) {
                    createUser();
                }
            }
        });
    }

    private boolean editInputCheck() {
        if(mEditId.getText().toString().equals("") || mEditName.getText().toString().equals("") ||
                mEditPass.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private void createUser() {
        if(auto == null) return;

        auto.createUserWithEmailAndPassword(mEditId.getText().toString(), mEditPass.getText().toString())
                .addOnCompleteListener(MemberShipAcitivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                            updateUserDB(mEditId.getText().toString(), mEditName.getText().toString());
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUserDB(String id, String name) {
        UserModel userModel = new UserModel(auto.getUid(), id, name);
        userDB.push().setValue(userModel);
    }

}
