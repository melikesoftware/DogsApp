package com.example.dogsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dogsapp.databinding.FragmentDogsBinding

import com.example.dogsapp.viewmodel.DogViewModel
import com.example.dogsapp.R

class DogsFragment : Fragment() {

    private  var dogsId=0
    private lateinit var dogViewModel: DogViewModel
    private var _binding: FragmentDogsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil<FragmentDogsBinding>.inflate(inflater,R.layout.fragment_dogs ,container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            dogsId= DogsFragmentArgs.fromBundle(it).dogsId

        }

        dogViewModel= ViewModelProvider(this).get(DogViewModel::class.java)
        dogViewModel.getFromData(dogsId)





        observeLiveData()


    }

    fun observeLiveData(){
        dogViewModel.dogLiveData.observe(viewLifecycleOwner, Observer { dog->
            dog?.let {
                binding.selectedDog=dog

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}