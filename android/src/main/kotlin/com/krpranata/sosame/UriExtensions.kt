package com.krpranata.sosame

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException

@Throws(IOException::class)
fun File.getUrisForPath(context: Context, providerAuthority: String): Uri {
    return FileProvider.getUriForFile(context, providerAuthority, this)
}
