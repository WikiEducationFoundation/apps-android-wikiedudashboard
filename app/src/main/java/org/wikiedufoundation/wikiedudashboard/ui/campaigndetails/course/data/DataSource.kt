package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.data

object DataSource {

    fun getCourseList(): List<Course> {
        val list = ArrayList<Course>()

        list.add(Course(1, "first course", "Justin",
                "University of Lagos", "500", "1000",
                "250", "300", "450"))

        list.add(Course(2, "second course", "Rita Dominic",
                "University of Lagos", "500", "1000",
                "250", "300", "450"))

        return list
    }
}