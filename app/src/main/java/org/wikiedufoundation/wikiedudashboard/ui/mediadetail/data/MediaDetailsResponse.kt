package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data

/**
 * [MediaDetailsResponse] model class
 * @constructor primary constructor
 *
 * @property query query statement
 * ***/
class MediaDetailsResponse(
    val query: Query
) {
    override fun toString(): String {
        return "MediaDetailsResponse(query=$query)"
    }
}
