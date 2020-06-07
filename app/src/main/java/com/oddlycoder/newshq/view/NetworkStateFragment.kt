package com.oddlycoder.newshq.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oddlycoder.newshq.R

class NetworkStateFragment : Fragment() {


    companion object {
        fun newInstance() : NetworkStateFragment {
            return NetworkStateFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_network_state, container, false)
        return view
    }
}