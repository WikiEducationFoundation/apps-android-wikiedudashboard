package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data

import java.io.Serializable

/**
 * [CourseUploadList] model class
 * @constructor primary constructor
 *
 * @property uploads list of course uploads
 * ***/
class CourseUploadList(
    val uploads: List<CourseUpload>
) : Serializable
