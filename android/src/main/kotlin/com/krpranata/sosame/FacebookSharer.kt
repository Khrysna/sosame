package com.krpranata.sosame

import android.content.Context
import android.content.Intent
import android.net.Uri


data class FacebookStoryOption(
    val backgroundImageUri: Uri,
    val appId: String,
    val mimeType: String,
    val stickerImageUri: Uri?,
)

internal class FacebookSharer {
    fun shareStory(context: Context, option: FacebookStoryOption) {
        Intent("com.facebook.stories.ADD_TO_STORY").apply {
            setDataAndType(option.backgroundImageUri, option.mimeType)

            putExtra("com.facebook.platform.extra.APPLICATION_ID", option.appId)

            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            if (option.stickerImageUri != null) {
                putExtra("interactive_asset_uri", option.stickerImageUri)
                context.grantUriPermission(
                    ApplicationPackageNames.FACEBOOK,
                    option.stickerImageUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION,
                )
            }

            if (context.packageManager.resolveActivity(this, 0) != null) {
                context.startActivity(this)
            }
        }
    }
}
