package com.mncgroup.core.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import com.mncgroup.core.network.DifferentDeviceException
import com.mncgroup.core.network.NotLoggedInException
import com.mncgroup.core.network.Result
import kotlinx.coroutines.supervisorScope
import java.lang.ref.WeakReference

object InternetUtil : LiveData<Boolean>() {

    private var broadcastReceiver: BroadcastReceiver? = null
    private lateinit var context: WeakReference<Context>

    fun init(context: Context) {
        this.context = WeakReference(context)
    }

    private val connectivityManager: ConnectivityManager?
        get() = context.get()?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    val isInternetOn: Boolean
        get() {
            val connectivityManager = connectivityManager
            when {
                connectivityManager == null -> return false
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                    val activeNetwork = connectivityManager.activeNetwork ?: return false
                    val capability = connectivityManager.getNetworkCapabilities(activeNetwork)
                            ?: return false
                    val hasCapabilityInternet = capability.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    val hasCapabilityNotRestricted = capability.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
                    val hasTransportCellular = capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    val hasTransportWifi = capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    return hasCapabilityInternet && hasCapabilityNotRestricted && (hasTransportCellular || hasTransportWifi)
                }
                else -> {
                    val activeNetworkInfo = connectivityManager.activeNetworkInfo ?: return false
                    val hasCapabilityInternet = activeNetworkInfo.isConnectedOrConnecting
                    val hasTransportCellular = activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
                    val hasTransportWifi = activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
                    return hasCapabilityInternet && (hasTransportCellular || hasTransportWifi)
                }
            }
        }

    override fun onActive() {
        registerBroadCastReceiver()
    }

    override fun onInactive() {
        unRegisterBroadCastReceiver()
    }

    @Suppress("DEPRECATION")
    private fun registerBroadCastReceiver() {
        if (broadcastReceiver == null) {
            val filter = IntentFilter()
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)

            broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(_context: Context, intent: Intent) {
                    val extras = intent.extras
                    val info = extras?.getParcelable<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO)
                    value = info?.state == NetworkInfo.State.CONNECTED
                }
            }

            context.get()?.registerReceiver(broadcastReceiver, filter)
        }
    }

    private fun unRegisterBroadCastReceiver() {
        if (broadcastReceiver != null) {
            context.get()?.unregisterReceiver(broadcastReceiver)
            broadcastReceiver = null
        }
    }
}

@Keep
object NoInternetConnection : Exception("No Connection")

suspend fun <T : Any> runIfConnectedOrResultException(body: suspend () -> Result<T>): Result<T> =
        try {
            if (InternetUtil.isInternetOn) {
                supervisorScope { body() }
            } else {
                Result.Exception(NoInternetConnection)
            }
        } catch (dde: DifferentDeviceException) {
            // clear cached user & go to landing
            Result.Exception(dde)
        } catch (nle: NotLoggedInException) {
            // clear cached user & go to landing
            Result.Exception(nle)
        } catch (e: Exception) {
            Result.Exception(e)
        }