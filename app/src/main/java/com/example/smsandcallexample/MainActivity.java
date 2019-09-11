package com.example.smsandcallexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etMobileNumber, etMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMobileNumber = findViewById(R.id.etMobile);
        etMessage = findViewById(R.id.etMessage);

        checkRunTimePermissionForSms();
    }

    private void checkRunTimePermissionForSms() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE}, 101);
        } else {
            Toast.makeText(this, "All Permissions Granted ", Toast.LENGTH_LONG).show();
        }

    }

    public void send_Message(View view) {

        String number = etMobileNumber.getText().toString();

        String message = etMessage.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, message, null, null);
        Toast.makeText(this,"Message Sent Successfully",Toast.LENGTH_LONG).show();

    }
}
