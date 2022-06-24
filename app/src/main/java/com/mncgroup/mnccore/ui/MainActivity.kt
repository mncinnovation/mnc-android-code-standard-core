package com.mncgroup.mnccore.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.mncgroup.core.ui.BaseActivity
import com.mncgroup.core.ui.EXTRA_HEADERS
import com.mncgroup.core.ui.EXTRA_TITLE
import com.mncgroup.core.ui.WebViewActivity
import com.mncgroup.core.util.ext.*
import com.mncgroup.core.util.load
import com.mncgroup.mnccore.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            ivImage.load("https://img.shields.io/github/v/release/mncinnovation/mnc-android-code-standard-core.svg?label=latest")

            btnShowSnackbar.setOnClickListener {
                showSnackbar(it, "Ini snackbar") {
                    Toast.makeText(this@MainActivity, "closed snackbar", Toast.LENGTH_SHORT).show()
                }
            }
            btnOpenAppSetting.setOnClickListener {
                startActivity(Intents.createOpenAppSettingsIntent(this@MainActivity))
            }
            btnOpenLocationSetting.setOnClickListener {
                startActivity(Intents.createOpenLocationSettings())
            }
            btnShowAppCompatAlertAction.setOnClickListener {
                showAppCompatAlert("This is a message. Will open web view") {
                    Toast.makeText(this@MainActivity, "Test", Toast.LENGTH_SHORT).show()
                    val headers: MutableMap<String, String> = mutableMapOf()
                    headers["token"] = "5454wvybw9y9"
                    startActivity(
                        Intent(this@MainActivity, WebViewActivity::class.java)
                            .apply {
                                putExtra(EXTRA_TITLE, "Webview")
                                putExtra(EXTRA_HEADERS, headers.toMap() as Serializable)
                                data =
                                    Uri.parse("https://cloud.google.com/speech-to-text/docs/libraries")
                            }
                    )
                }
            }
            btnShowInputName.setOnClickListener {
                showAppCompatAlertInputAction(
                    "Please to Input Fullname",
                    "Input",
                    "Kirim",
                    "Kembali",
                    "Fullname",
                    true
                ) { input ->
                    Toast.makeText(this@MainActivity, "Namanya adalah : $input", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            btnShowDatePicker.setOnClickListener {
                showDatePickerAction(null, null) { day, month, year ->
                    Toast.makeText(
                        this@MainActivity, "selected date is " + day +
                                " / " + month +
                                " / " + year, Toast.LENGTH_SHORT
                    ).show()
                }.show()
            }
        }
    }
}