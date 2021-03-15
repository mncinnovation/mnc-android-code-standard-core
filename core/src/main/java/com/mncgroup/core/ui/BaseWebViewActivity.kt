package com.mncgroup.core.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.core.content.FileProvider
import com.mncgroup.core.databinding.ActivityBaseWebViewBinding
import com.mncgroup.core.util.ext.*
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.jvm.Throws


/**
 * Base Web view activity that support to handle action file chooser and open camera if needed
 *
 */
abstract class BaseWebViewActivity : BaseActivity() {
    lateinit var binding: ActivityBaseWebViewBinding

    private var mFilePathCallback: ValueCallback<Array<Uri>>? = null
    private var mCameraPhotoPath: String? = null

    protected val url: String? by lazy {
        intent?.dataString
    }
    protected val title: String? by argument(EXTRA_TITLE, null)
    protected val headers: Map<String, String>? by argument(EXTRA_HEADERS, null)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webview.apply {
            webChromeClient = object : WebChromeClient() {
                override fun onShowFileChooser(
                    webView: WebView?,
                    filePathCallback: ValueCallback<Array<Uri>>?,
                    fileChooserParams: FileChooserParams?
                ): Boolean {

                    if (mFilePathCallback != null) {
                        mFilePathCallback?.onReceiveValue(null)
                    }
                    mFilePathCallback = filePathCallback

                    checkPermissionOrOpenCamera()
                    return true

                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    if (this@BaseWebViewActivity.title == null) {
                        setPageTitle(title ?: "")
                    }
                }

                override fun onProgressChanged(view: WebView?, newProgress: Int) {

                }
            }
            clearCache(true)
            clearHistory()
            settings.apply {
                javaScriptEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                domStorageEnabled = true
                pluginState = WebSettings.PluginState.ON
                allowFileAccess = true
            }
            WebView.setWebContentsDebuggingEnabled(true)
        }

        binding.toolbar.let {
            setSupportActionBar(it)
        }
        showToolbar(true)
        showToolbarBack(true)
        showToolbarTitle(true)
    }

    override fun onBackPressed() {
        when {
            binding.webview.url == url -> finish()
            binding.webview.canGoBack() -> {
                binding.webview.goBack()
            }
            else -> {
                super.onBackPressed();
            }
        }
    }

    protected fun setPageTitle(title: String?) {
        setToolbarTitle(title ?: "")
    }

    protected fun loadPageByUrl(url: String?) {
        url?.let { binding.webview.loadUrl(it) }
    }

    protected fun loadPageByUrl(url: String?, headers: Map<String, String>) {
        url?.let { binding.webview.loadUrl(it, headers) }
    }


    private fun checkPermissionOrOpenCamera() {
        when (checkPermissionsAll(
            listOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )) {
            PermissionResult.Granted -> openFileChooser()
            PermissionResult.DialogNeeded, PermissionResult.NeedRequest -> {
                requestPermissions(
                    RC_CAMERA,
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timestamp = tempFileFormat.format(Date())
        val imageFileName = "JPEG_${timestamp}_"
        val storageDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", storageDir)
    }

    private fun openFileChooser() {
        var takePictureIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent?.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
                takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath)
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            if (photoFile != null) {
                mCameraPhotoPath = "file:${photoFile.absolutePath}"
                val uriForFile = FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.paytren.image.provider",
                    photoFile
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile)
            } else {
                takePictureIntent = null
            }
        }

        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.type = "image/*"

        val intentArray = if (takePictureIntent != null) {
            arrayOf(takePictureIntent)
        } else {
            emptyArray()
        }

        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)

        startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = mFilePathCallback
        if (requestCode != INPUT_FILE_REQUEST_CODE || callback == null) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }

        var results: Array<Uri>? = null
        if (resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                if (mCameraPhotoPath != null) {
                    results = arrayOf(Uri.parse(mCameraPhotoPath))
                }
            } else {
                val dataString = data.dataString
                if (dataString != null) {
                    results = arrayOf(Uri.parse(dataString))
                }
            }
        }

        callback.onReceiveValue(results)
        mFilePathCallback = null
        return
    }

    companion object {
        private const val INPUT_FILE_REQUEST_CODE = 1
        private const val RC_CAMERA = 101
    }
}