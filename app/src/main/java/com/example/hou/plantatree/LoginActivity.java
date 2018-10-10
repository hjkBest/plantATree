package com.example.hou.plantatree;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    private TextView userRegistration;
    private EditText logEmail,logPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logEmail=(EditText)findViewById(R.id.login_mail);
        logPassword=(EditText)findViewById(R.id.login_password);
        userRegistration=(TextView)findViewById(R.id.tv_register);
        loginButton=(Button)findViewById(R.id.login_button);

        firebaseAuth=FirebaseAuth.getInstance();

        FirebaseUser user=firebaseAuth.getCurrentUser();
        progressDailog = new ProgressDialog(this);

        if(user!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,TreePage.class));
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate(logEmail.getText().toString(),logPassword.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });



    }

    private void validate(String logEmail, String logPassword){
        progressDailog.setMessage("validate now");
        progressDailog.show();

        firebaseAuth.signInWithEmailAndPassword(logEmail,logPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDailog.dismiss();
                    Toast.makeText(LoginActivity.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,TreePage.class));
                }else{
                    progressDailog.dismiss();
                    Toast.makeText(LoginActivity.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
