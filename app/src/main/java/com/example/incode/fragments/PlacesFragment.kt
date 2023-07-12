package com.example.incode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incode.adapters.PlaceAdapter
import com.example.incode.api.RetrofitClient
import com.example.incode.databinding.FragmentPlacesBinding
import com.example.incode.models.TestResultsOne
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlacesFragment: Fragment() {
    private lateinit var bind: FragmentPlacesBinding

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentPlacesBinding.inflate(inflater)

        val client = RetrofitClient.apiInstance
        val adp = PlaceAdapter(requireContext())


        val call = client.getPlaces("gyms", "Nairobi")
        call.enqueue(object: Callback<TestResultsOne>{
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    adp.list.submitList(list)
                    Toast.makeText(requireContext(), list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()

            }

        })
        bind.rec.apply{
            adapter = adp
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        return bind.root
    }
}