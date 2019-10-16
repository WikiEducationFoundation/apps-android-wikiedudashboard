package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.FileUsage

/**
 * RecyclerView adapter for course list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement o item click through a lambda
 * ***/
class FileUsesRecyclerAdapter internal constructor(
        layoutId: Int
//        private val onClickListener: () -> Unit
) : SingleLayoutAdapter<FileUsage>(layoutId) {

    /**
     * Use [filesClicked] to handle the item's click
     * @param file item's object
     ***/
    fun filesClicked(file: FileUsage) {
//        onClickListener(file)
    }

}