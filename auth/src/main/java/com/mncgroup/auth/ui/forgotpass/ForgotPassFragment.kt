package com.mncgroup.auth.ui.forgotpass

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mncgroup.auth.R

class ForgotPassFragment : Fragment() {

    companion object {
        fun newInstance() = ForgotPassFragment()
    }

    private lateinit var viewModel: ForgotPassViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgot_pass, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForgotPassViewModel::class.java)
        // TODO: Use the ViewModel

    }

}