package com.microboat.common.utils

import java.util.*

object StringUtils {

    fun firstCharacterIndex(charSequence: CharSequence): Int {
        return (charSequence.indices).firstOrNull { charSequence[it] > ' ' } ?: -1
    }

    fun camelToUnderline(param: String?): String {
        if (param == null || "" == param.trim { it <= ' ' }) {
            return ""
        }
        val len = param.length
        val sb = StringBuilder(len)
        sb.append(Character.toLowerCase(param[0]))
        (1 until len).map { param[it] }
            .forEach {
                when {
                    Character.isUpperCase(it) -> {
                        sb.append(UNDERLINE)
                        sb.append(Character.toLowerCase(it))
                    }

                    else -> sb.append(it)
                }
            }
        return sb.toString()
    }

    fun underlineToCamel(str: String?): String {
        if (str == null || "" == str.trim { it <= ' ' }) {
            return ""
        }
        val len = str.length
        val sb = StringBuilder(len)
        var i = 0
        while (i < len) {
            val c = str[i]
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(str[i]))
                }
            } else {
                sb.append(c)
            }
            i++
        }
        return sb.toString()
    }

    const val UNDERLINE = '_'
}

/**
 * Returns `true` if the contents of this string is equal to the word "true" or "1",
 * ignoring case, and `false` otherwise.
 */
fun String.toBool(defaultValue: Boolean = false): Boolean {
    if (this.isBlank()) return defaultValue

    return try {
        this.toBoolean() || this == "1"
    } catch (e: Exception) {
        defaultValue
    }
}

fun StringBuilder.appendlnIfNotEmpty(): StringBuilder {
    if (this.isNotEmpty()) {
        this.appendln()
    }
    return this
}

/** Appends a line separator to this StringBuilder. */
fun StringBuilder.appendln(): StringBuilder = append(LINE_SEPARATOR)

fun Any?.tinyString(): String? {
    return when {
        this == null -> null
        this is String -> this
        this is Array<*> -> this.first().tinyString()
        this is Collection<*> -> this.first().tinyString()
        else -> this.toString()
    }
}

fun String.truncate(limit: Int, truncated: String = "..."): String {
    return when {
        this.length > limit -> this.substring(0, limit) + truncated
        else -> this
    }
}

fun String?.append(str: String?, split: String = " "): String? {
    return when {
        this.isNullOrBlank() -> str
        str.isNullOrBlank() -> this
        else -> "$this$split$str"
    }
}

fun String?.trimToNull(): String? {
    return when {
        this.isNullOrBlank() -> null
        else -> this
    }
}

fun String?.appendln(): String? {
    return when {
        this.isNullOrBlank() -> this
        this.endsWith(LINE_SEPARATOR) -> this
        else -> "${this}${LINE_SEPARATOR}"
    }
}

fun String?.appendln(str: String?): String? {
    return this.append(str, LINE_SEPARATOR)
}

fun String?.notNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty()
}

fun String?.notNullOrBlank(): Boolean {
    return !this.isNullOrBlank()
}

fun String?.flatten(defaultValue: String = "null"): String {
    return this?.replace(LINE_SEPARATOR, "\\n") ?: defaultValue
}

private const val LINE_SEPARATOR = "\n"

fun String.capitalize(): String {
    return this.replaceFirstChar { it.titlecase(Locale.ENGLISH) }
}

fun String.decapitalize(): String {
    return this.replaceFirstChar { it.lowercase(Locale.ENGLISH) }
}

/**
 * Extension function on String to retrieve the index of the nth occurrence of a specified character.
 * Returns -1 if the character is not found or if the occurrence number is invalid.
 */
fun String.getNthIndex(char: Char, n: Int): Int {
    return this.asSequence().withIndex()
        .filter { it.value == char }
        .drop(n - 1)
        .firstOrNull()?.index ?: -1
}