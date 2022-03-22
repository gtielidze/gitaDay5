package com.myapplication.gitaday5

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.CompoundButton
import android.widget.Switch
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity


class FirstTaskActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private var bluetooth: Switch? = null

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private var wiFi: Switch? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_task)

        bluetooth = findViewById(R.id.turnOnBluetooth)
        wiFi = findViewById(R.id.turnOnWifi)

        bluetooth?.setOnCheckedChangeListener { compoundButton, b ->


        }
        wiFi?.setOnCheckedChangeListener { buttonView, isChecked ->
            val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                startActivityForResult(panelIntent, 0)
            } else {
                wifi.isWifiEnabled = true
            }

        }

        bluetooth?.setOnCheckedChangeListener { _, isChecked ->
            val blue = BluetoothAdapter.getDefaultAdapter()
            if (isChecked) {
                blue.enable()
            } else {
                blue.disable()
            }
        }


    }


}