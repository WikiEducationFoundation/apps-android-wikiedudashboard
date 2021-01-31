package org.wikiedufoundation.wikiedudashboard.util

/**
 * Use Kotlin [ExtensionFunctionType] to use [filterOrEmptyList] as a list extension function.
 * @param block safety the list function
 * @return the filtered list or emptyList()
 ***/
fun <T> List<T>?.filterOrEmptyList(block: (T) -> Boolean): List<T> =
    this?.filter(block) ?: emptyList()
