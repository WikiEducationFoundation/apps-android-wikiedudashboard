package org.wikiedufoundation.wikiedudashboard.util

fun <T> List<T>?.filterOrEmptyList(block: (T) -> Boolean): List<T> =
        this?.filter(block) ?: emptyList()