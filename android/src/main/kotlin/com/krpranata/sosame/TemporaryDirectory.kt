package com.krpranata.sosame

import java.io.File

class TemporaryDirectory(private val temporaryFolder: File) {
    fun clear() {
        val files = temporaryFolder.listFiles()

        if (temporaryFolder.exists() && !files.isNullOrEmpty()) {
            files.forEach { it.delete() }
            temporaryFolder.delete()
        }
    }

    fun put(file : File): File {
        val folder = temporaryFolder

        if (temporaryFolder.exists().not()) {
            temporaryFolder.mkdirs()
        }

        val newFile = File(folder, file.name)
        file.copyTo(newFile, true)

        return newFile
    }
}
