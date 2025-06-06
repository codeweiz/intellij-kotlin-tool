package com.microboat.common.utils

import java.net.URL

object ResourceUtils {

    // A map that caches the contents of resources that have been read
    private val resourceCache = mutableMapOf<String, String>()

    /**
     * Reads the contents of the specified resource from the classpath and returns it as a String.
     * If the resource has already been read, then the cached value is returned.
     *
     * @param resourceName The name of the resource to read.
     * @return The contents of the resource as a String, or an empty String if the resource could not be found.
     */
    fun readResource(resourceName: String): String {
        return resourceCache.safeComputeIfAbsent(resourceName) {
            (ResourceUtils::class.java.classLoader.getResourceAsStream(resourceName)
                ?: ResourceUtils::class.java.getResourceAsStream(resourceName))
                ?.use {
                    it.readString(Charsets.UTF_8)
                } ?: ""
        } ?: ""
    }

    // A map that caches the URLs of resources that have been found
    private val resourceURLCache = mutableMapOf<String, URL>()

    /**
     * Attempts to find the URL of the specified resource on the classpath.
     * If the resource URL has already been found, then the cached value is returned.
     *
     * @param resourceName The name of the resource to find.
     * @return The URL of the resource, or null if the resource could not be found.
     */
    fun findResource(resourceName: String): URL? {
        return resourceURLCache.safeComputeIfAbsent(resourceName) {
            doFindResource(resourceName)
        }
    }

    /**
     * Attempts to find the URL of the specified resource using the class loader.
     *
     * @param resourceName The name of the resource to find.
     * @return The URL of the resource, or null if the resource could not be found.
     */
    private fun doFindResource(resourceName: String): URL {
        return ResourceUtils::class.java.classLoader.getResource(resourceName)
            ?: ResourceUtils::class.java.getResource(resourceName)!!
    }
}
