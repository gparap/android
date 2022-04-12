package gparap.apps.movies.utils

import gparap.apps.movies.model.ArticleModel
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun createAttributionDetails_isAttributionTextCorrect() {
        //create expected text
        val articleTitle = "Abraham Lincoln (1930 film)"
        val expectedAttributionText =
            "This page uses material from the Wikipedia article $articleTitle, " +
                    "which is released under " +
                    "the Creative Commons Attribution-ShareAlike 3.0 Unported License (view authors)."

        //create actual text
        val articleModel = ArticleModel(articleTitle, "", "", "")
        val actualAttributionText = Utils.createAttributionDetails(articleModel)

        assertEquals(expectedAttributionText, actualAttributionText.toString())
    }
}