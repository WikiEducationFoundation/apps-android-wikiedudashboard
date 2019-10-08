package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaCategory
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.view.MediaDetailFragment
import java.util.*

/**
 * RecyclerView adapter for category list data
 * @constructor secondary constructor to initialize [MediaDetailFragment] variable
 * ***/
class CategoryListRecyclerAdapter internal constructor(
        layoutId: Int
//        private val onClickListener: () -> Unit
) : SingleLayoutAdapter(layoutId) {
    private var mediaCategoryList: List<MediaCategory> = ArrayList()

    override fun getObjForPosition(position: Int): Any = mediaCategoryList[position]

    override fun getItemCount(): Int = mediaCategoryList.size

    /**
     * Set [MediaCategory] type courses
     * @param courses list of Media courses data
     * ***/
    fun setData(courses: List<MediaCategory>) {
        this.mediaCategoryList = courses
    }

    fun mediaClicked(mediaCategory: MediaCategory) {
//        onClickListener(mediaCategory)
    }

}