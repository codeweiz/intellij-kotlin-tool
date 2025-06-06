package com.microboat.common.logger

import com.microboat.common.exception.ProcessCanceledException
import org.apache.commons.lang3.exception.ExceptionUtils

interface ILogger {
    fun log(msg: String)

    fun trace(msg: String)

    fun debug(msg: String)

    fun info(msg: String)

    fun warn(msg: String)

    fun error(msg: String)
}

fun ILogger.traceWarn(msg: String, e: Throwable) {
    this.warn(msg)
    this.traceError(e)
}

fun ILogger.traceError(msg: String, e: Throwable) {
    this.error(msg)
    this.traceError(e)
}

fun ILogger.traceError(e: Throwable) {
    if (e !is ProcessCanceledException) {
        this.trace(ExceptionUtils.getStackTrace(e))
    }
}