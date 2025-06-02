package com.microboat.common.concurrent

import kotlin.jvm.Throws

interface CountLatch {

    /**
     * 等待所有请求都完成
     * */
    @Throws(InterruptedException::class)
    fun waitFor()

    /**
     * 等待所有请求都完成，并超时
     * */
    fun waitFor(msTimeout: Long): Boolean

    /**
     * 请求
     * */
    fun down()

    /**
     * 完成
     * */
    fun up()

    /**
     * 总数
     * */
    fun count(): Int
}
