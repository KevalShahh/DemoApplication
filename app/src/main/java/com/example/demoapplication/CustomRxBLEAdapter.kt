package com.example.demoapplication

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRxBLEAdapter(var context: Context,var list: ArrayList<CustomRxBleModel>) :
    RecyclerView.Adapter<CustomRxBLEAdapter.RxViewHolder>() {
    class RxViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var rxname: TextView =itemView.findViewById(R.id.tv_rxble_name)
        var rxaddress: TextView =itemView.findViewById(R.id.tv_rxble_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RxViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.custom_rx_ble,parent,false)
        return RxViewHolder(view)
    }

    override fun onBindViewHolder(holder: RxViewHolder, position: Int) {
        holder.rxaddress.text = list[position].RxBleAddress.macAddress.toString()
        if (list[position].getRxAddress().name==null){
            holder.rxname.text="_"
        }else{
            holder.rxname.text = list[position].RxBleAddress.name.toString()
        }
        holder.itemView.setOnClickListener {
            val intent= Intent(context,RxDeviceControlActivity::class.java)
            intent.putExtra("rxaddress",list[position].RxBleAddress.macAddress.toString())
            intent.putExtra("device",list[position].RxBleAddress.bluetoothDevice)
            Log.d("TAG", "onBindViewHolder: "+list[position].RxBleAddress.bluetoothDevice)
            Log.d("TAG", "onBindViewHolder: "+list[position].RxBleAddress.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

}
