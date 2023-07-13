package com.example.incode.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.incode.databinding.ButtonsRecBinding

class ButtonsRec(private val list: ArrayList<String>) : RecyclerView.Adapter<ButtonsRec.MyHolder>(){
    inner class MyHolder(private val bind: ButtonsRecBinding): RecyclerView.ViewHolder(bind.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        ButtonsRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        TODO("Not yet implemented")
    }
}