package com.example.incode.adapters;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.incode.R
import com.example.incode.databinding.PlaceRecBinding
import com.example.incode.models.Ride

class RidesAdapter(val cont: Context) : RecyclerView.Adapter<RidesAdapter.MyHolder>(){
    inner class MyHolder(val bind: PlaceRecBinding): RecyclerView.ViewHolder(bind.root){
        fun setData(ride: Ride){
            bind.image.setImageResource(R.drawable.routes)
            bind.distance.text = "Cancel ride"
            bind.description.text = "Ride from ${ride.origin} to ${ride.destination} by ${ride.driver.name} around ${ride.time}"
            bind.address.text = "${ride.date}"

            bind.distance.setOnClickListener{
                Toast.makeText(cont, "Coming soon", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val diffUtil = object: DiffUtil.ItemCallback<Ride>(){
        override fun areItemsTheSame(oldItem: Ride, newItem: Ride): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: Ride, newItem: Ride): Boolean {
            return oldItem == newItem
        }

    }

    val list = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        PlaceRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = list.currentList.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(list.currentList[position])
    }
}
