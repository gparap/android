package gparap.apps.movies.utils

import android.text.Html
import gparap.apps.movies.model.ArticleModel

object Utils {
    /**
     * Returns a text that has markup objects containing the necessary attribution information
     */
    fun createAttributionDetails(article: ArticleModel): android.text.Spanned {
        return Html.fromHtml(
            "This page uses material from the Wikipedia article " +
                    "<a href=\"" + article.articleLink + "\">" + article.title + ",</a> " +
                    "which is released under the " +
                    "<a href=\"" + article.licenseLink + "\">Creative Commons Attribution-ShareAlike 3.0 Unported License</a> " +
                    "(" +
                    "<a href=\"" + article.authorsLink + "\">view authors</a>" +
                    ").", Html.FROM_HTML_MODE_LEGACY
        )
    }

    /**
     * Returns a text that has markup objects containing the link to watch a movie
     */
    fun createWatchMovieLink(linkHref: String, linkText: String): android.text.Spanned {
        return Html.fromHtml(
            "&#62;&#62;&#160;" +
                    "<a href=\"" + linkHref + "\">" +
                    linkText +
                    "</a>" +
                    "&#160;&#60;&#60;", Html.FROM_HTML_MODE_LEGACY
        )
    }
}