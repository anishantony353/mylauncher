package com.anish.mylauncher.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anish.mylauncher.R
import com.anish.mylauncher.databinding.RvRowItemBinding

import com.anish.mylauncher.model.AppInfo
import com.anish.mylauncher.model.ClickListener

class Adapter(listener: ClickListener): RecyclerView.Adapter<Adapter.MyViewHolder>() {

    var appInfos = ArrayList<AppInfo>()
    var listener = listener

    class MyViewHolder(rvRowBinding: RvRowItemBinding, listener: ClickListener) : RecyclerView.ViewHolder(rvRowBinding.root) {

        var binding = rvRowBinding
        var listener = listener

        fun bind(appInfo: AppInfo, pos:Int){
            binding.appInfo = appInfo
            binding.pos = pos
            binding.appInfoClickListener = listener
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var rvRowBinding: RvRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_row_item,
            parent,
            false
        )

        return MyViewHolder(rvRowBinding,listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(appInfos.get(position), position)
    }

    override fun getItemCount(): Int {
        return appInfos.size
    }

}