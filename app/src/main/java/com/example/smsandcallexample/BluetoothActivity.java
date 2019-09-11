package com.example.smsandcallexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class BluetoothActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch bleSwitch;
    PackageManager packageManager;
    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_manager);

        packageManager = getPackageManager();
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Bluetooth Not available", Toast.LENGTH_SHORT).show();
        }

        bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        bleSwitch = findViewById(R.id.switchBle);
        bleSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(bluetoothAdapter.isEnabled())
        {
            bleSwitch.setChecked(true);
        }
        else
        {
            bleSwitch.setChecked(false);
        }


    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean switchStatus) {
        if (switchStatus) {
            // enable
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivityForResult(intent, 101);
        } else {
            // disable
            bluetoothAdapter.disable();
            Toast.makeText(this,"BLE Disabled",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, "BLE Successfully Enabled", Toast.LENGTH_LONG).show();
            bleSwitch.setChecked(true);
        } else {
            Toast.makeText(this, "BLE Failed to Enable", Toast.LENGTH_LONG).show();
            bleSwitch.setChecked(false);
        }
    }
}
