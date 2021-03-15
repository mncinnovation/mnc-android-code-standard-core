package com.mncgroup.core.ui

import android.content.Context
import androidx.fragment.app.Fragment

class BaseFragment : Fragment() {
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}