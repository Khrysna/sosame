package com.krpranata.sosame

import android.content.Context
import android.content.Intent
import android.net.Uri


data class InstagramFeedOption(
    val mediaUri: Uri,
    val sourceApplication: String,
    val mimeType: String,
)

data class InstagramStoryOption(
    val backgroundImageUri: Uri,
    val sourceApplication: String,
    val mimeType: String,
    val stickerImageUri: Uri?,
)

internal class InstagramSharer {
    fun shareStory(context: Context, option: InstagramStoryOption) {
        Intent("com.instagram.share.ADD_TO_STORY").apply {
            setDataAndType(option.backgroundImageUri, option.mimeType)

            putExtra("source_application", option.sourceApplication)

            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            if (option.stickerImageUri != null) {
                putExtra("interactive_asset_uri", option.stickerImageUri)
                context.grantUriPermission(
                    ApplicationPackageNames.INSTAGRAM,
                    option.stickerImageUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION,
                )
            }

            if (context.packageManager.resolveActivity(this, 0) != null) {
                context.startActivity(this)
            }
        }
    }

    fun shareFeed(context: Context, option: InstagramFeedOption) {
        Intent("com.instagram.share.ADD_TO_FEED").apply {
            setDataAndType(option.mediaUri, option.mimeType)
            putExtra("source_application", option.sourceApplication)
            putExtra(Intent.EXTRA_STREAM, option.mediaUri)

            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            if (context.packageManager.resolveActivity(this, 0) != null) {
                context.startActivity(this)
            }
        }
    }
}
