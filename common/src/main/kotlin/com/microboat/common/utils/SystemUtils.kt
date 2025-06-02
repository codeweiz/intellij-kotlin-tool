package com.microboat.common.utils

import org.apache.commons.lang3.StringUtils

object SystemUtils {
    val userName: String
        get() {
            var userName: String
            try {
                userName = System.getenv("USERNAME")
                if (!StringUtils.isBlank(userName)) {
                    return userName
                }
            } catch (ignored: Exception) {
            }

            try {
                userName = System.getProperty("user.name")
                if (!StringUtils.isBlank(userName)) {
                    return userName
                }
            } catch (ignored: Exception) {
            }

            return "Admin"
        }

    val userHome: String
        get() {

            var userName: String
            try {
                userName = System.getenv("USERHOME")
                if (!StringUtils.isBlank(userName)) {
                    return userName
                }
            } catch (ignored: Exception) {
            }

            try {
                userName = System.getProperty("user.home")
                if (!StringUtils.isBlank(userName)) {
                    return userName
                }
            } catch (ignored: Exception) {
            }

            return "~"
        }

    val LINE_SEPARATOR: String = System.lineSeparator()

    @Deprecated("Use LINE_SEPARATOR instead", ReplaceWith("LINE_SEPARATOR"))
    private val NEW_LINE = System.lineSeparator()

    val isWindows: Boolean
        get() {
            val OS = System.getProperty("os.name")
            return OS.lowercase().contains("windows")
        }

    @Deprecated("Use LINE_SEPARATOR instead", ReplaceWith("LINE_SEPARATOR"))
    fun newLine(): String {
        return NEW_LINE ?: "\r\n"
    }
}
