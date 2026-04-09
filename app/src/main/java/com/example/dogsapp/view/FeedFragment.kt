package com.example.dogsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager



import com.example.dogsapp.adapter.DogAdapter
import com.example.dogsapp.databinding.FragmentFeedBinding
import com.example.dogsapp.model.Dog
import com.example.dogsapp.viewmodel.FeedViewModel


class FeedFragment : Fragment() {
    private lateinit var feedViewModel:FeedViewModel
    private val dogAdapter= DogAdapter(arrayListOf())
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedViewModel= ViewModelProvider(this).get(FeedViewModel::class.java)
        feedViewModel.refreshData()

        binding.dogs.layoutManager= GridLayoutManager(context,2)
        binding.dogs.adapter=dogAdapter


        binding.swipeRefreshLayout.setOnRefreshListener {

            binding.dogs.visibility= View.GONE
            binding.dogsProgessBar.visibility= View.VISIBLE

            feedViewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing=false
        }
        observeData()
    }


    fun observeData(){
        feedViewModel.dogs.observe (viewLifecycleOwner,Observer{  dogs->
            dogs?.let {
                binding.dogs.visibility= View.VISIBLE
                dogAdapter.updateDogList(dogs as ArrayList<Dog>)
            }

        })

        feedViewModel.dogLoading.observe (viewLifecycleOwner, Observer{ loading->

            loading?.let {
                if(it) {
                    binding.dogsProgessBar.visibility = View.VISIBLE
                    binding.dogs.visibility = View.GONE

                }
                else{
                    binding.dogsProgessBar.visibility= View.GONE
                }

            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}