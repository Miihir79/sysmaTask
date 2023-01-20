package com.mihir.sysmatask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mihir.sysmatask.databinding.ItemBinding
import com.mihir.sysmatask.model.ClassItem

class Adapter :RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var items = ArrayList<ClassItem>()

    inner class ViewHolder(binding: ItemBinding):RecyclerView.ViewHolder(binding.root){
        val title:TextView = binding.title
        val desc: TextView = binding.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = items[position].title
        holder.desc.text = items[position].body

        holder.title.setOnClickListener {
            holder.desc.isVisible = true
        }
    }

    override fun getItemCount() = items.size

    fun setItems(reItems:ArrayList<ClassItem>){
        items = reItems
        notifyDataSetChanged()
    }
}