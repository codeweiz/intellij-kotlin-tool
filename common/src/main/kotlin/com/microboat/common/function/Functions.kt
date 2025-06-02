package com.microboat.common.function

import java.util.Objects
import java.util.function.Function
import java.util.function.Predicate


object Functions {

    fun <T> from(predicate: Predicate<T>): Function<T, Boolean> {
        return Function {
            predicate.test(it)
        }
    }

    fun <T> nullAs(defaultVal: T): Function<T?, T> {
        Objects.requireNonNull(defaultVal)
        return Function { value ->
            value ?: defaultVal
        }
    }

    fun <T, R> nullAs(function: Function<T?, R>, defaultVal: R): Function<T?, R> {
        Objects.requireNonNull(defaultVal)
        return Function { value ->
            if (value == null) defaultVal else function.apply(value)
        }
    }

}