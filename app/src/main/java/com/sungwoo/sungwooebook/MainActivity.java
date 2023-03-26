package com.sungwoo.sungwooebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sungwoo.sungwooebook.Login.MemberShipAcitivity;
import com.sungwoo.sungwooebook.Utils.UtilsLog;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auto;
    private Button mLoginBtn;
    private TextView mAssing, find_id_pw;
    private EditText mUserId, mPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auto.signInWithEmailAndPassword(mUserId.getText().toString(), mPassWord.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), BottomAcitivity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });

        mAssing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MemberShipAcitivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mAssing = (TextView) findViewById(R.id.assine);
        mUserId = (EditText) findViewById(R.id.edit_id);
        mPassWord = (EditText) findViewById(R.id.edit_pw);
        find_id_pw = (TextView) findViewById(R.id.find_id_pw);

        auto = FirebaseAuth.getInstance();
    }
}