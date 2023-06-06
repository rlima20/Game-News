package com.example.gamenews.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.gamenews.R
import com.example.gamenews.model.States

/**
 * Format date to date news.
 * This extension returns a string from the first letter until to letter on index 16
 * @receiver
 */
fun String.formatDateToDateNews() {
    this.substring(startIndex = 0, endIndex = 16)
}

/**
 * Get async image painter.
 * This extension returns a Painter with the image if the imaRequest has State.Success. If not,
 * it will return a generic painter.
 * @receiver [ImageRequest]
 * @return [Painter]
 */
@Composable
fun ImageRequest.getAsyncImagePainter(
    onStateChanged: (state: States) -> Unit = {}
): Painter {
    if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Success) {
        onStateChanged(States.SUCCESS)
    } else if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Loading) {
        onStateChanged(States.LOADING)
    } else {
        onStateChanged(States.ERROR)
    }

    return if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Success) {
        rememberAsyncImagePainter(this)
    } else {
        painterResource(id = R.drawable.placeholder)
    }
}

fun Fragment.hasInternet(): Boolean {
    val connMgr =
        requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connMgr.getNetworkCapabilities(connMgr.activeNetwork)
        capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NET_CAPABILITY_VALIDATED)
    } else {
        @Suppress("DEPRECATION")
        connMgr.activeNetworkInfo?.isConnected == true
    }
}

fun String.removeSpaces(): String = this.replace(" ", "")
