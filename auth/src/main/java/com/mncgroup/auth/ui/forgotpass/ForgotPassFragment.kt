package com.mncgroup.auth.ui.forgotpass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mncgroup.auth.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPassFragment : Fragment() {

    companion object {
        fun newInstance() = ForgotPassFragment()
    }

    private val viewModel: ForgotPassViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgot_pass, container, false)
    }

}