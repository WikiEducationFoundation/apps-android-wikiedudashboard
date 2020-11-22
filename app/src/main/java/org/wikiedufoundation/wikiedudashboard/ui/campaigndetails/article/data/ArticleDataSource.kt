package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.data

/**
 * Article Datasource dummy data
 ***/
object ArticleDataSource {

    fun getArticles(): List<Articles> {
        val list = ArrayList<Articles>()
        list.add(
            Articles(
                1, "Article 1", "400",
                "300", "250", "Hello word"
            )
        )

        list.add(
            Articles(
                2, "Article 2", "400",
                "300", "250", "Hello number 2"
            )
        )

        list.add(
            Articles(
                3, "Article 3", "500",
                "30k", "200", "Hello number 3"
            )
        )
        return list
    }
}
