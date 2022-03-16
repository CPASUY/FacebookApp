package com.example.facebookapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.facebookapp.databinding.FragmentHomeBinding
import com.example.facebookapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private var _binding:FragmentProfileBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {

        @JvmStatic
        fun newInstance() = ProfileFragment().apply {
        }
    }
}