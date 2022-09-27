package com.example.baatcheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {


    FirebaseAuth auth=FirebaseAuth.getInstance();;
    FirebaseDatabase database;

    private EditText etEmail;
    private EditText etPassWord;
    private TextView clickForSignUp;
    private Button SignInButton;
    private Button GoogleButton;
    private Button FaceBookButton;
    private TextView SignUpWithPhone;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);













        setContentView(R.layout.activity_sign_in);
          getSupportActionBar().hide();
        etEmail=findViewById(R.id.etEmail);
        etPassWord=findViewById(R.id.etPassWord);
        clickForSignUp=findViewById(R.id.clickForSignUp);
        SignInButton=findViewById(R.id.SignInButton);
        GoogleButton=findViewById(R.id.GoogleButton);
        FaceBookButton=findViewById(R.id.FaceBookButton);
        SignUpWithPhone=findViewById(R.id.SignUpWithPhone);

        progressDialog=new ProgressDialog(SignInActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account");

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String email=etEmail.getText().toString();
                String password=etPassWord.getText().toString();

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     progressDialog.dismiss();
                     if(task.isSuccessful())
                     {
                         startActivity(new Intent(SignInActivity.this,MainActivity.class));
                     }
                     else
                         Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        if(auth.getCurrentUser()!=null)
        {
            startActivity(new Intent(SignInActivity.this,MainActivity.class));
        }

        clickForSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });

    }
}