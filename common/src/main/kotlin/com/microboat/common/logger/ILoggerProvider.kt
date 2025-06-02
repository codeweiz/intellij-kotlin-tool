package com.microboat.common.logger

import kotlin.reflect.KClass

interface ILoggerProvider {

    fun getLogger(name: String): ILogger

    fun getLogger(clazz: KClass<*>): ILogger

    fun getLogger(clazz: Class<*>): ILogger
}
