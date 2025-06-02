package com.microboat.common.files

typealias FileFilter = (FileWrap) -> Boolean

object FileFilters {

    val defaultHandle: FileFilter = { true }

    fun filterFile(filter: (file: FileWrap) -> Boolean): FileFilter {
        return { it.file.isDirectory || filter(it) }
    }

    fun filterDirectory(filter: (file: FileWrap) -> Boolean): FileFilter {
        return { it.file.isFile || filter(it) }
    }
}

fun FileFilter.andThen(nextHandle: FileFilter): FileFilter = { file ->
    this(file) && nextHandle(file)
}