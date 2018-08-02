package com.example.jijie.pe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class SCANActivity extends AppCompatActivity {
    private String codeResult;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
    public void onScanQrcode(View v){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Scan error", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scan réussi, le numéro de référence:" + result.getContents(), Toast.LENGTH_LONG).show();
                codeResult = result.getContents();
                Intent intent=new Intent();

                intent.setClass(SCANActivity.this,FormActivity.class);
                intent.putExtra("reference",codeResult);
                startActivity(intent);

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private boolean existRef(int codeResult){
        boolean exist = false;

        return exist;
    }
}
