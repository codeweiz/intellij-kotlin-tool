package com.microboat.common.utils

class Functions

fun <T, R> (() -> T?).map(transform: (T?) -> R?): () -> R? {
    return {
        transform(this())
    }
}
