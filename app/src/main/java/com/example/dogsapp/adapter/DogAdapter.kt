package com.example.dogsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.databinding.ItemDogsBinding
import com.example.dogsapp.model.Dog

import com.example.dogsapp.view.FeedFragmentDirections
import com.example.dogsapp.R


class DogAdapter(val dogList: ArrayList<Dog>): RecyclerView.Adapter<DogAdapter.AdapterHolder>(),
    DogClickListener {
    class AdapterHolder(val binding: ItemDogsBinding): RecyclerView.ViewHolder(binding.root) {

    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogAdapter.AdapterHolder {
        val binding= DataBindingUtil.inflate<ItemDogsBinding>(LayoutInflater.from(parent.context),
            R.layout.item_dogs,parent,false)
            return AdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: DogAdapter.AdapterHolder, position: Int) {


        holder.binding.dog=dogList[position]
        holder.binding.listener=this




    }

    override fun getItemCount(): Int {
        return dogList.size
    }
    fun updateDogList(newDogList: ArrayList<Dog>){
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onDogClicked(view: View) {

        val textView = view.findViewById<TextView>(R.id.dogId)
        val id=textView.text.toString().toInt()
     val action= FeedFragmentDirections.actionFeedFragmentToDogsFragment(id)
        Navigation.findNavController(view).navigate(action)


    }
}
