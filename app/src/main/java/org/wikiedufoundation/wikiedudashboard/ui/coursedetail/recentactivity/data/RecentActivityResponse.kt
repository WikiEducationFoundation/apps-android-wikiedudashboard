package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data

import java.io.Serializable

/**
 * [RecentActivityResponse] response model class
 * @constructor primary constructor initializing course property
 *
 * @property course return of the response
 * ***/
data class RecentActivityResponse(val course: Course) : Serializable