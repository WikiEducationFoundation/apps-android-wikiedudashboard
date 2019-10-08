package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivity
import java.util.*

/**
 * RecyclerView adapter for recent activities
 * ***/
class RecentActivityRecyclerAdapter(
        layoutId: Int
) : SingleLayoutAdapter(layoutId) {
    private var activities: List<RecentActivity> = ArrayList()

    override fun getObjForPosition(position: Int): Any = activities[position]

    /**
     * Set [RecentActivity] type list of edited activities list
     * @param edited list of courses
     * ***/
    fun setData(edited: List<RecentActivity>) {
        this.activities = edited
    }

    override fun getItemCount(): Int = activities.size

}
