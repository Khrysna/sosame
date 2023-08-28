package com.krpranata.sosame

import android.content.pm.PackageManager

fun PackageManager.isApplicationInstalled(packageName: String): Boolean {
    val packages = getInstalledApplications(PackageManager.GET_META_DATA)

    return packages.any { it.packageName == packageName }
}
