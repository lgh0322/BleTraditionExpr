package com.vaca.myapplication

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var bleAdapter:BleScanAdapter
    lateinit var bluetoothLeScanner: BluetoothLeScanner
    lateinit var mBluetoothAdapter: BluetoothAdapter
    var bluetoothManager: BluetoothManager? = null
    private val mLeScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            bleAdapter.addDevice(result.rssi,result.device)
        }

        override fun onBatchScanResults(results: List<ScanResult>) {
            super.onBatchScanResults(results)
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bleAdapter= BleScanAdapter(this)
        gg.adapter=bleAdapter
        val linearLayoutManager=LinearLayoutManager(this)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        gg.layoutManager=linearLayoutManager


        bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        mBluetoothAdapter = bluetoothManager!!.adapter
        bluetoothLeScanner = mBluetoothAdapter.bluetoothLeScanner
        bluetoothLeScanner.startScan(mLeScanCallback)
    }
}