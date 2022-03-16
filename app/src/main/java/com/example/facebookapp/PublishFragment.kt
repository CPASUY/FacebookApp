package com.example.facebookapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.facebookapp.databinding.FragmentHomeBinding
import com.example.facebookapp.databinding.FragmentPublishBinding

class PublishFragment : Fragment() {
    private  var _binding:FragmentPublishBinding?= null
    private val binding get() = _binding!!

    var listener: OnNewPost?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublishBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.publicarBtn.setOnClickListener {
            val description = binding.descPostText.text.toString()

            //Publicacion
            listener?.let {
                it.onNewPost("A","Carolina Pasuy", "20/02/2022", description)
            }
        }


        // Inflate the layout for this fragment
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnNewPost{
        fun onNewPost(id:String,name:String,date:String,description:String)
    }
    companion object {

        @JvmStatic
        fun newInstance() = PublishFragment().apply {
        }
    }
}