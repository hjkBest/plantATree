package com.example.hou.plantatree;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText regEmail, regPassword;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        firebaseAuth=FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //update data to database
                    String user_email=regEmail.getText().toString().trim();
                    String user_password=regPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration fail!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    private void setupUIViews(){
        regEmail=(EditText)findViewById(R.id.register_mail);
        regPassword=(EditText)findViewById(R.id.register_password);
        regButton=(Button)findViewById(R.id.register_button);
        userLogin=(TextView)findViewById(R.id.tv_login);
    }

    private Boolean validate(){
        Boolean result=false;

        String email=regEmail.getText().toString();
        String password=regPassword.getText().toString();

        if(email.isEmpty()&&password.isEmpty())
        {
            Toast.makeText(this,"Please enter all details",Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;
    }

}
