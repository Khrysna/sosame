package com.krpranata.sosame

import android.content.Context
import android.content.Intent
import android.net.Uri

data class WhatsAppOption(
    val phoneNumber: String?, val text: String?, val mediaUri: Uri?, val mimeType: String
)

internal class WhatsAppSharer {
    fun share(context: Context, option: WhatsAppOption) {
        Intent(Intent.ACTION_SEND).apply {
            setPackage("com.whatsapp")

            if (option.phoneNumber != null) {
                putExtra("jid", option.phoneNumber + "@s.whatsapp.net")
            }

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
