package com.microboat.common.files

typealias FileHandle = (FileWrap) -> Unit

@FunctionalInterface
object FileHandles {

    val defaultHandle: FileHandle = {}

    fun from(handle: (file: FileWrap) -> Unit): FileHandle {
        return {
            handle(it)
        }
    }

    fun collectFiles(files: MutableCollection<FileWrap>): FileHandle {
        return {
            files.add(it)
        }
    }

    fun <T> collectFiles(files: MutableCollection<T>, transform: (FileWrap) -> T): FileHandle {
        return {
            files.add(transform(it))
        }
    }
}

fun FileHandle.andThen(nextHandle: FileHandle): FileHandle = { file ->
    this(file)
    nextHandle(file)
}
