package com.example.incode.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.incode.databinding.FragmentPlaceBinding
import com.example.incode.databinding.PlaceRecBinding
import com.example.incode.databinding.RightPlaceRecBinding
import com.example.incode.models.Place

class PlacesAdapter: RecyclerView.Adapter<PlacesAdapter.MyHolder>() {
    private var layoutPosition: Int? = null

    private val listDiffer = object: DiffUtil.ItemCallback<Place>(){
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }

    }

    val asyncList = AsyncListDiffer(this, listDiffer)
    inner class MyHolder(private val bind: FragmentPlaceBinding): RecyclerView.ViewHolder(bind.root){
        fun setData(place: Place){
            bind.placeName.text = place.title
            bind.placeImage.setImageResource(place.image!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        FragmentPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun getItemCount() = asyncList.currentList.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        layoutPosition = position % 2
        holder.setData(asyncList.currentList[position])
    }
}