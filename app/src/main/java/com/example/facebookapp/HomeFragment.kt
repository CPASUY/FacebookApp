package com.example.facebookapp

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookapp.databinding.FragmentHomeBinding
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

    override fun onNewPost(id: String, name: String, date: String, description: String) {
        val post = Post("A","Carolina","22/02/2022",description)
        adapter.addPost(post)
    }
}