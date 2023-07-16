package com.example.incode.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.incode.databinding.PlaceRecBinding
import com.example.incode.databinding.RightPlaceRecBinding
import com.example.incode.models.Driver

class DriversAdapter: RecyclerView.Adapter<DriversAdapter.MyHolder>() {
    private var layoutType: Int? = null

    private val diffUtil = object: DiffUtil.ItemCallback<Driver>(){
        override fun areItemsTheSame(oldItem: Driver, newItem: Driver): Boolean {
            return oldItem.car!!.numberPlate ==  newItem.car!!.numberPlate
        }

        override fun areContentsTheSame(oldItem: Driver, newItem: Driver): Boolean {
            return oldItem == newItem
        }

    }

    val driversList = AsyncListDiffer(this, diffUtil)


    inner class MyHolder(private val bind: ViewBinding): RecyclerView.ViewHolder(bind.root){
            fun setData(driver: Driver){
                if(layoutType == 0){
                    val mBind = (bind as PlaceRecBinding)
                    mBind.name.text = driver.name
                    mBind.description.text = "You can reach ${driver.name!!.split(" ")[0]} on ${driver.phone} who drives a ${driver.car!!.name} identified with ${driver.car!!.numberPlate}"
                    mBind.distance.text = driver.distance
                    mBind.address.text = driver.location
                }else{
                    val mBind = (bind as RightPlaceRecBinding)
                    mBind.name.text = driver.name
                    mBind.description.text = "You can reach ${driver.name!!.split(" ")[0]} on ${driver.phone} who drives a ${driver.car!!.name} identified with ${driver.car!!.numberPlate}"

                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if(layoutType != 0){
        MyHolder(
            PlaceRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }else{
        MyHolder(
            RightPlaceRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = driversList.currentList.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        layoutType = position % 2
        holder.setData(driversList.currentList[position])
    }
}