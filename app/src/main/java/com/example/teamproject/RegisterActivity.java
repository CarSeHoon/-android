package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;         //파이어베이스 인증
    private DatabaseReference mDatabaseRef;     //실시간 데이터베이스
    private EditText mEtemail, mEtpwd, mEtbirth, mEtname, mEtpwd2, mEtscore;          //회원가입 입력필드
    private Button mBtnRegister, pwcheck;                //회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("teamproject");

        pwcheck = findViewById(R.id.pwcheckbutton);
        mEtscore = findViewById(R.id.et_score);
        mEtemail = findViewById(R.id.et_email);
        mEtpwd = findViewById(R.id.et_pwd);
        mEtpwd2 = findViewById(R.id.et_pwd2);
        mBtnRegister = findViewById(R.id.btn_register);
        mEtbirth = findViewById(R.id.et_birth);
        mEtname = findViewById(R.id.et_name);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 처리 시작
                String strEmail = mEtemail.getText().toString();
                String strPwd = mEtpwd.getText().toString();
                String strName = mEtname.getText().toString();
                String strBirth = mEtbirth.getText().toString();
                String strScore = mEtscore.getText().toString();
                
                //Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setName(strName);
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId(firebaseUser.getEmail());
                            account.setBirthday(strBirth);
                            account.setPassword(strPwd);
                            account.setScore(strScore);

                            //setValue : 데이터베이스에 Insert (삽입) 행위
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish(); //현재 액티비티 파괴
                        } else {
                            Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        pwcheck = findViewById(R.id.pwcheckbutton);
        pwcheck.setOnClickListener(v -> {
            if(mEtpwd.getText().toString().equals(mEtpwd2.getText().toString())){
                pwcheck.setText("일치");
            }else{
                Toast.makeText(RegisterActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show();
            }
        });

    }
}