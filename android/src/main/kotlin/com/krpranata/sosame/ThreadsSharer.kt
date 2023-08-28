package com.krpranata.sosame

import android.content.Context
import android.content.Intent
import android.net.Uri

data class ThreadsOption(val mediaUri: Uri, val mimeType: String)

internal class ThreadsSharer {
    fun shareMedia(context: Context, option: ThreadsOption) {
        Intent(Intent.ACTION_SEND).apply {
            setPackage("com.instagram.barcelona")
            putExtra(Intent.EXTRA_STREAM, option.mediaUri)

            type = option.mimeType

            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            if (context.packageManager.resolveActivity(this, 0) != null) {
                context.startActivity(Intent.createChooser(this, null))
            }
        }
    }
}
