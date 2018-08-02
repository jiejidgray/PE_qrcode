package com.example.jijie.pe;

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

    private EditText userEmail, userPassword;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();
        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               if(validate()){
                   //upload data to the database
                   String user_email = userEmail.getText().toString().trim();
                   String user_password = userPassword.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              Toast.makeText(RegisterActivity.this,"Registration Successful", Toast.LENGTH_SHORT).show();
                          }else{
                              Toast.makeText(RegisterActivity.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                           }

                       }
                   });
               };
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    private void setupUIViews(){
        userEmail = (EditText)findViewById(R.id.idEmail);
        userPassword = (EditText)findViewById(R.id.idPassword);
        regButton = (Button)findViewById(R.id.buttonRegister);
        userLogin = (TextView)findViewById(R.id.signedin);

    }

    private Boolean validate(){
        Boolean result = false;
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
       if(email.isEmpty()&& password.isEmpty()){
           Toast.makeText(this,"Please complete the form",Toast.LENGTH_SHORT).show();
       } else {
           result = true;
       }
        return result;
    }
}
