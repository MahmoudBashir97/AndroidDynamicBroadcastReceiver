@file:Suppress("DEPRECATION")

package com.example.broadcastagain.ui

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.broadcastagain.ConnectionChangeReceiver
import com.example.broadcastagain.R
import com.thanosfisherman.wifiutils.WifiUtils
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionErrorCode
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay


class SecondActivity : AppCompatActivity() {
    lateinit var receiver: ConnectionChangeReceiver
    lateinit var intentFilter: IntentFilter

    var wifiManager: WifiManager? = null

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        wifiManager = applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        this.title = "Second Activity"
        initLoading()
        initEvents()
        sendBroadcast(
            Intent("")
                .apply {
                    putExtra("","")
                }
        )
    }

    private fun initLoading() {
        receiver = ConnectionChangeReceiver()
        intentFilter=  IntentFilter("someAction")
        registerReceiver(receiver,intentFilter)
        registeringReceiver()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun initEvents() {
        this.findViewById<Button>(R.id.check_btn)
            .setOnClickListener {
//                sendBroadcast(Intent("someAction"))
                scanWifi()
            }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun scanWifi() {
        registeringReceiver()
        wifiManager?.calculateSignalLevel(1000)
        wifiManager?.startScan()

    }
    private fun connectToSpecificWifi(){
//        val con = WifiConfiguration()
//        val networkSSID ="Ghaly"
//        val pass = "123456789m"
//        con.SSID = "\"" + networkSSID + "\""
//        con.wepKeys[0] = "\"" + pass + "\""
//        con.wepTxKeyIndex = 0
//        con.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)
//        con.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40)
//        con.preSharedKey = "\""+ pass +"\""
//
//
//        val wifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        wifiManager.addNetwork(con)

         if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
//        val list = wifiManager.configuredNetworks
//        for (i in list) {
//            if (i.SSID != null) {
//                Log.d("?????","ssid : ${i.SSID}")
//                wifiManager.disconnect()
//                wifiManager.enableNetwork(i.networkId, true)
//                wifiManager.reconnect()
//                break
//            }
//        }

        Toast.makeText(this@SecondActivity,"mmmmmmmmmmmmm",Toast.LENGTH_LONG).show()

        WifiUtils.withContext(this)
            .connectWith("Ghaly", "123456789m")
            .setTimeout(80000)
            .onConnectionResult(object:ConnectionSuccessListener{
                override fun success() {
                    Toast.makeText(this@SecondActivity,"connected : success",Toast.LENGTH_LONG).show()

                }

                override fun failed(errorCode: ConnectionErrorCode) {
                    //
                    Toast.makeText(this@SecondActivity,"errorCode : ${errorCode.name}",Toast.LENGTH_LONG).show()
                }
            }).start()

    }

    override fun onPause() {
        super.onPause()
       // unregisterReceiver(receiver)

    }

    override fun onResume() {
        super.onResume()
       // registerReceiver(receiver,intentFilter)
    }

    override fun onStart() {
        super.onStart()
       // registerReceiver(receiver,intentFilter)
        registeringReceiver()
    }

    private fun registeringReceiver(){
        this.registerReceiver(
            wifiReceiver,
            IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        )
    }

    private var wifiReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.R)
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) {
                val mScanResults: List<ScanResult> = wifiManager?.scanResults ?: emptyList()
                Log.d("?????","OthersSSID : here")

                mScanResults.forEach { scanResult->
                    Log.d("?????","OthersSSID : ${scanResult.SSID}")

                    if (scanResult.SSID.contains("Ghaly")
                    ) {
                        connectToSpecificWifi()
                    }else{
                       Log.d("?????","OthersSSID : ${scanResult.SSID}")
                    }
                }
            }
        }
    }
}