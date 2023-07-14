/*
 * Copyright (c) 2022-2023 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.movies.utils

import android.text.Html
import gparap.apps.movies.model.ArticleModel
import gparap.apps.movies.services.MovieService
import gparap.apps.movies.services.RetrofitClient

object Utils {
    /**
     * Returns a text that has markup objects containing the necessary attribution information
     */
    fun createAttributionDetails(article: ArticleModel): android.text.Spanned {
        return Html.fromHtml(
            "This page uses material from the Wikipedia article " +
                    "<a href=\"" + article.articleLink + "\">" + article.title + ",</a> " +
                    "which is released under the " +
                    "<a href=\"" + article.licenseLink + "\">Creative Commons Attribution-ShareAlike License 4.0</a> " +
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

    /** Returns an implementation of the Movies API endpoints defined by the service interface. */
    fun createMoviesService() : MovieService {
        return RetrofitClient()
            .build(AppConstants.BASE_URL)
            .create(MovieService::class.java)
    }
}