package com.example.jijie.pe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.Normalizer;

public class FormActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private Dossier doc;
    private TextView submitForm;
    private EditText form_name;
    private EditText form_prenom;

    private Button getV;
// ...
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
            }
            case R.id.refreshMenu:{

            }
        }
        return super.onOptionsItemSelected(item);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        this.doc = new Dossier();

        // get the content of qrcode
        Intent intent = getIntent();
        String codeResult = intent.getStringExtra("codeResult");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        TextView ref =(TextView) findViewById(R.id.form_ref);
        ref.setText(codeResult);
        submitForm = (TextView)findViewById(R.id.submitForm);
        getV =(Button)findViewById(R.id.getV);



        submitForm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submit();
            }
        });




    }

    private void submit(){
        form_name = (EditText) findViewById(R.id.form_name);
        form_prenom =(EditText) findViewById(R.id.form_prenom);
        doc.setNom(form_name.toString());
        doc.setPrenom(form_prenom.toString());
        mDatabase.setValue(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(FormActivity.this,"Submit Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FormActivity.this,Main2Activity.class));

                        } else {
                            Toast.makeText(FormActivity.this,"Submit Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(FormActivity.this,LoginActivity.class));
    }

}
