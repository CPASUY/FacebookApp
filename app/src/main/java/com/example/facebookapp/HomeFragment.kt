package com.example.facebookapp

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookapp.databinding.FragmentHomeBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(),PublishFragment.OnNewPost {

    private var _binding:FragmentHomeBinding?= null
    private val binding get() = _binding!!
    //State
    private val adapter = PostAdapter()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root

        val postRecycler = binding.postRecycler
        postRecycler.setHasFixedSize(true)
        postRecycler.layoutManager = LinearLayoutManager(activity)

        postRecycler.adapter = adapter
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()

    }

    override fun onNewPost(name:String,date:String,description:String,city:String,imagePost:String) {
        val post = Post(name,date,description,city,imagePost)
        adapter.addPost(post)
    }
    override fun onPause() {
        super.onPause()

        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        adapter.onPause(sharedPreferences)

    }
    override fun onResume() {
        super.onResume()

        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        adapter.onResume(sharedPreferences)


    }
}