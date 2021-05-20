package com.mncgroup.mnccore.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.mncgroup.core.util.ext.Intents
import com.mncgroup.core.util.ext.showAppCompatAlert
import com.mncgroup.core.util.ext.showAppCompatAlertInputAction
import com.mncgroup.core.util.ext.showDatePickerAction
import com.mncgroup.mnccore.R
import com.mncgroup.mnccore.databinding.ActivityMainBinding

class MainActivity : Activity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnOpenAppSetting.setOnClickListener {
                startActivity(Intents.createOpenAppSettingsIntent(this@MainActivity))
            }
            btnOpenLocationSetting.setOnClickListener {
                startActivity(Intents.createOpenLocationSettings())
            }
            btnShowAppCompatAlertAction.setOnClickListener {
                showAppCompatAlert("This is a message"){
                    Toast.makeText(this@MainActivity, "Test", Toast.LENGTH_SHORT).show()
                }
            }
            btnShowInputName.setOnClickListener {
                showAppCompatAlertInputAction("Please to Input Fullname","Input","Kirim","Kembali","Fullname",true) { input ->
                    Toast.makeText(this@MainActivity, "Namanya adalah : $input", Toast.LENGTH_SHORT).show()
                }
            }

            btnShowDatePicker.setOnClickListener {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    showDatePickerAction(null,null) { day, month, year ->
                        Toast.makeText(this@MainActivity, "selected date is " + day +
                                " / " + month +
                                " / " + year, Toast.LENGTH_SHORT).show();
                    }.show()
                }
            }
        }
    }
}