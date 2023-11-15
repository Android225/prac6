package com.example.kotlinpract1.UI.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.kotlinpract1.R

class Fragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment1, container, false)


        val buttonToFragment2 = view.findViewById<Button>(R.id.button_to_fragment2)
        buttonToFragment2.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }

        val buttonToFragment3 = view.findViewById<Button>(R.id.button_to_fragment3)
        buttonToFragment3.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment3)
        }
        return view
    }
}

