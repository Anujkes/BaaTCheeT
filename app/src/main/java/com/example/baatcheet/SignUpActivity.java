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

import com.example.baatcheet.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth=FirebaseAuth.getInstance();;
    FirebaseDatabase database;
    private EditText etUserName;
    private EditText etEmail;
    private EditText etPassWord;
    private TextView alreadyHaveAccount;
    private Button SignUpButton;
    private Button GoogleButton;
    private Button FaceBookButton;
    private TextView SignUpWithPhone;

   ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        etUserName =findViewById(R.id.etUsernName);
        etEmail =findViewById(R.id.etEmail);
        etPassWord =findViewById(R.id.etPassWord);
        alreadyHaveAccount=findViewById(R.id.clickForSignUp);
        SignUpButton =findViewById(R.id.SignInButton);
        GoogleButton =findViewById(R.id.GoogleButton);
        FaceBookButton =findViewById(R.id.FaceBookButton);
        SignUpWithPhone=findViewById(R.id.SignUpWithPhone);
        database=FirebaseDatabase.getInstance();


        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("We're creating your account");
     SignUpButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             progressDialog.show();
             String name=etUserName.getText().toString();
             String email=etEmail.getText().toString();
             String password=etPassWord.getText().toString();

             auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
               progressDialog.dismiss();
                     if(task.isSuccessful())
                     {
                         Users user=new Users(name,email,password);
                         String id=task.getResult().getUser().getUid();
                         database.getReference().child("Users").child(id).setValue(user);

                         Toast.makeText(SignUpActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                     }
                     else
                     {
                         Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 }
             });


         }
     });

     alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
         }
     });

    }
}