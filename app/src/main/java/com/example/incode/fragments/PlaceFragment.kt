package com.example.incode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.incode.databinding.FragmentPlaceBinding
import com.example.incode.models.PlaceResult
import com.example.incode.uitils.Constants
import com.squareup.picasso.Picasso

class PlaceFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentPlaceBinding
    private final val PLACE = "place"
    private var place: PlaceResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            place = it.getSerializable(PLACE) as PlaceResult
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentPlaceBinding.inflate(inflater)

        ///binding the place data to its views
        bind.scholarName.text = place!!.name
        bind.placeName.text = "Along -> ${place!!.formatted_address}"

        if (place!!.photos == null){

        }else{
            val url1 = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=${place!!.photos[0].width}&photoreference=${place!!.photos[0].photo_reference}&key=${Constants.yes}"
            Picasso.get().load(url1).into(bind.placeImage)
        }

        bind.reference.text = "${place!!.types[0]}${place!!.types[1]}${place!!.types.size} "
        bind.status.text = "Opened now -> ${place!!.opening_hours.open_now.toString()}"
        bind.rating.text = "Rated at: ${place!!.rating.toString()}/5"
        bind.people.text = "likes: ${place!!.user_ratings_total}"

        Picasso.get().load(place!!.icon).into(bind.image)

        bind.gym.setOnClickListener(this::onClick)
        bind.back.setOnClickListener(this::onClick)
        return bind.root
    }

    companion object{
        @JvmStatic
        fun newInstance(place: PlaceResult) = PlaceFragment().apply {
            arguments = Bundle().apply {
                putSerializable(PLACE,place )
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val manager = childFragmentManager.beginTransaction().replace(bind.frame.id, fragment)
        manager.commit()
    }

    override fun onClick(v: View?) {
        if(v == bind.gym){
            bind.cont.alpha = 0.1f
            bind.cont1.alpha = 0.1f
            val fragment = DriveThereFragment()
            replaceFragment(fragment)
            //bind.transformationOverview.startTransform()
        }

        if(v == bind.back){
            requireActivity().finish()
        }
    }
}