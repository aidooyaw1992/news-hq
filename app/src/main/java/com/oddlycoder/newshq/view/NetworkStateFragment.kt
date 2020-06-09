package com.oddlycoder.newshq.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.work.*
import com.oddlycoder.newshq.R
import com.oddlycoder.newshq.netutils.NetworkInterface
import com.oddlycoder.newshq.netutils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_network_state.*
import java.util.concurrent.TimeUnit

class NetworkStateFragment : Fragment() {

    private lateinit var mImageButtonTestConnection: ImageButton
    private lateinit var networkConnectivity: NetworkInterface

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
        mImageButtonTestConnection = view.findViewById(R.id.image_button_test_connection)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * is connection available switch to article fragment
         * on user click
         */
        image_button_test_connection?.setOnClickListener(View.OnClickListener { v ->
            val networkState = NetworkUtils.isNetworkConnected(view.context)
            if (networkState) {
                val fragment = ArticlesFragment.newInstance()
                val fm = activity?.supportFragmentManager

                fm?.beginTransaction()
                    ?.replace(R.id.fragment_container, fragment)
                    ?.setCustomAnimations(R.anim.enter_right, R.anim.exit_left)
                    ?.commit()
            } else {
                Toast.makeText(view.context, "Cannot find capable network", Toast.LENGTH_SHORT).show()
            }
        })
    }

}