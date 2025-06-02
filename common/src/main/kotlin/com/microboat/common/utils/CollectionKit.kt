package com.microboat.common.utils

import java.util.*

/**
 * Safely reduces the collection by applying [operation] from left to right.
 * Returns `null` if the collection is empty (avoids throwing exceptions like `reduce`).
 */
inline fun <S, T : S> Iterable<T>.reduceSafely(operation: (acc: S, T) -> S): S? {
    val iterator = this.iterator()
    if (!iterator.hasNext()) return null
    var accumulator: S = iterator.next()
    while (iterator.hasNext()) {
        accumulator = operation(accumulator, iterator.next())
    }
    return accumulator
}

/** Returns `true` if the array is not null and not empty. */
fun <T> Array<T>?.notNullOrEmpty(): Boolean = this != null && this.isNotEmpty()

/** Returns `true` if the collection is not null and not empty. */
fun Collection<*>?.notNullOrEmpty(): Boolean = this != null && this.isNotEmpty()

/** Converts a List to an ArrayList. If already an ArrayList, returns itself. */
fun <E> List<E>.asArrayList(): ArrayList<E> = if (this is ArrayList<E>) this else ArrayList(this)

// Predefined immutable collection classes (using class names to avoid type issues)
private val IMMUTABLE_COLLECTION_CLASS_NAMES = setOf(
    emptyList<Any>().javaClass.name,
    Collections.emptyList<Any>().javaClass.name,
    Collections.singletonList("").javaClass.name,
    emptySet<Any>().javaClass.name,
    Collections.emptySet<Any>().javaClass.name,
    // Java 9+ immutable collections
    "java.util.ImmutableCollections\$ListN",
    "java.util.ImmutableCollections\$List12",
    "java.util.ImmutableCollections\$SetN",
    "java.util.ImmutableCollections\$Set12",
    // Kotlin's emptyList/emptySet implementations
    "kotlin.collections.EmptyList",
    "kotlin.collections.EmptySet"
)

/**
 * Checks if the given object is a **mutable** collection.
 * @return `true` if the object is a mutable collection, `false` otherwise.
 */
fun Any.isMutableCollection(): Boolean {
    if (this !is Collection<*>) return false
    return this::class.java.name !in IMMUTABLE_COLLECTION_CLASS_NAMES
}