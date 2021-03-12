package com.vaca.myapplication;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BleScanAdapter extends RecyclerView.Adapter<BleScanAdapter.ViewHolder> {
    private List<BleBean> mBleData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    BluetoothManager bluetoothManager;
    public static String HEART_RATE_MEASUREMENT = "0000ffe1-0000-1000-8000-00805f9b34fb";
    private static BluetoothGattCharacteristic target_chara = null;

    public BleScanAdapter(Context context){
        this.mInflater = LayoutInflater.from(context);
        this.mBleData = new ArrayList<>();
        mContext = context;
    }

    public void addDevice(int rssi, BluetoothDevice bluetoothDevice) {
        int z=0;
        if(bluetoothDevice.getName()==null)return;
        if(bluetoothDevice.getName().length()==0)return;
        for(int k=0;k<mBleData.size();k++){
            if(mBleData.get(k).getBluetoothDevice().getName().equals(bluetoothDevice.getName())){
                z=1;
                break;
            }
        }
        if(z==0){
            mBleData.add(new BleBean(rssi,bluetoothDevice));
            notifyDataSetChanged();
        }

    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.ble_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BluetoothDevice bluetoothDevice=mBleData.get(position).getBluetoothDevice();
        holder.bleName.setText(bluetoothDevice.getName());
    }

    @Override
    public int getItemCount() {
        return mBleData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bleName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bleName=itemView.findViewById(R.id.da);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
