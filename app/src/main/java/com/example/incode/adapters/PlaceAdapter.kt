package com.example.incode.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.incode.R
import com.example.incode.SecondActivity
import com.example.incode.databinding.FragmentBreakOneBinding
import com.example.incode.models.PlaceResult
import com.example.incode.uitils.Constants
import com.squareup.picasso.Picasso

class PlaceAdapter(private val cont: Context) : RecyclerView.Adapter<PlaceAdapter.MyHolder>(){
    inner class MyHolder(private val bind: FragmentBreakOneBinding): RecyclerView.ViewHolder(bind.root){
        //var uri = ""
        fun setData(place: PlaceResult){

                if(place.photos == null){
                    bind.placeImage.setImageResource(R.drawable.church)
                    //uri += place.photos[i].photo_reference
                }else{
                    val url1 = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=${place.photos[0].width}&photoreference=${place.photos[0].photo_reference}&key=${Constants.yes}"
                    Picasso.get().load(url1).into(bind.placeImage)
                }
            bind.evening.text = place.name
            bind.placeName.text = place.business_status
            if (place.opening_hours == null){
                bind.circle1.text = "Opened: False"
            }else{
                bind.circle1.text = "Opened: ${place.opening_hours.open_now.toString()}"
            }
        }

        fun onCLicked(place: PlaceResult) {
            bind.root.setOnClickListener{
                val intent = Intent(cont, SecondActivity::class.java)
                intent.putExtra("place", place)
                cont.startActivity(intent)
            }
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

    override fun getItemCount() = 10
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val place = list.currentList[position]
        holder.setData(place)
        holder.onCLicked(place)
    }
}