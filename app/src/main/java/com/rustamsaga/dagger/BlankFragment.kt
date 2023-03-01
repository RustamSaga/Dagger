package com.rustamsaga.dagger

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

class BlankFragment : Fragment() {

    @Inject lateinit var loginViewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).loginComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

}