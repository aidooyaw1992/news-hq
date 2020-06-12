package com.oddlycoder.newshq.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.oddlycoder.newshq.R
import com.oddlycoder.newshq.netutils.NetworkUtils
import kotlinx.android.synthetic.main.fragment_network_state.*

class NetworkStateFragment : Fragment() {

    private lateinit var mImageButtonMoveToBookmarks: ImageButton
    private var retries = 3

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
        mImageButtonMoveToBookmarks = view.findViewById(R.id.image_button_move_toBookmarks)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImageButtonMoveToBookmarks.setOnClickListener {
            moveToArticleFrag()
        }
    }

    private fun moveToArticleFrag() {
        val fragment = ArticlesFragment.newInstance()
        val fm = activity?.supportFragmentManager
        fm?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.setCustomAnimations(R.anim.enter_right, R.anim.exit_left)
            ?.commit()
    }

}