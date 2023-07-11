package com.example.incode.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.incode.databinding.FragmentBreakOneBinding
import com.example.incode.models.PlaceResult
import com.squareup.picasso.Picasso

class PlaceAdapter(private val cont: Context) : RecyclerView.Adapter<PlaceAdapter.MyHolder>(){
    inner class MyHolder(private val bind: FragmentBreakOneBinding): RecyclerView.ViewHolder(bind.root){
        fun setData(place: PlaceResult){
            for(i in 0 until place.photos.size){
                if (place.photos[i].photo_reference.isNotEmpty()){
                    break
                }
            }
            bind.evening.text = place.name
            bind.placeName.text = place.business_status
            //Picasso.get().load(place.photos[0].photo_reference).into(bind.placeImage)
            Glide.with(cont)
                .asBitmap()
                .load(place.icon_mask_base_uri)
                .into(bind.placeImage);


        }
    }

    private val diffUtil = object: DiffUtil.ItemCallback<PlaceResult>(){
        override fun areItemsTheSame(oldItem: PlaceResult, newItem: PlaceResult): Boolean {
            return oldItem.place_id == newItem.place_id
        }

        override fun areContentsTheSame(oldItem: PlaceResult, newItem: PlaceResult): Boolean {
            return oldItem == newItem
        }

    }

    val list = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyHolder(
        FragmentBreakOneBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = list.currentList.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val place = list.currentList[position]
        holder.setData(place)
    }
}