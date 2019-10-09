package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaCategory

/**
 * RecyclerView adapter for category list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement o item click through a lambda
 * ***/
class CategoryListRecyclerAdapter internal constructor(
        layoutId: Int
//        private val onClickListener: () -> Unit
) : SingleLayoutAdapter<MediaCategory>(layoutId) {

    fun mediaClicked(mediaCategory: MediaCategory) {
//        onClickListener(mediaCategory)
    }

}