package com.mncgroup.mnccore.ui

import android.app.Activity
import android.os.Bundle
import com.mncgroup.mnccore.databinding.ActivityMainBinding

class MainActivity : Activity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}