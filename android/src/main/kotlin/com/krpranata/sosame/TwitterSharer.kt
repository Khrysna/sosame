package com.krpranata.sosame

import android.content.Context
import android.content.Intent
import android.net.Uri

data class TwitterOption(val text: String?, val mediaUri: Uri?, val mimeType: String)

internal class TwitterSharer {
    fun share(context: Context, option: TwitterOption) {
        Intent(Intent.ACTION_SEND).apply {
            setClassName("com.twitter.android", "com.twitter.composer.ComposerActivity")

            if (option.text != null) {
                putExtra(Intent.EXTRA_TEXT, option.text)
            }

            if (option.mediaUri != null) {
                putExtra(Intent.EXTRA_STREAM, option.mediaUri)
            }

            type = option.mimeType

            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            if (context.packageManager.resolveActivity(this, 0) != null) {
                context.startActivity(Intent.createChooser(this, null))
            }
        }
    }
}
