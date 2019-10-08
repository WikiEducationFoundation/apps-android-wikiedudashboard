package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.FileUsage
import java.util.*

/**
 * RecyclerView adapter for courses list data
 * @property mediaDetailFragment primary constructor property
 * ***/
class FileUsesRecyclerAdapter internal constructor(
        layoutId: Int
//        private val onClickListener: () -> Unit
) : SingleLayoutAdapter(layoutId) {
    private var fileUsageList: List<FileUsage> = ArrayList()

    override fun getObjForPosition(position: Int): Any = fileUsageList[position]

    /**
     * Set [FileUsage] type [courses] list of data
     * @param courses list of courses
     * ***/
    fun setData(fileUsageList: List<FileUsage>) {
        this.fileUsageList = fileUsageList
    }

    override fun getItemCount(): Int = fileUsageList.size

    fun filesClicked(file: FileUsage) {
//        onClickListener(file)
    }

}