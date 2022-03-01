/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.quiz.utils

import android.content.Context
import com.google.gson.Gson
import gparap.apps.quiz.data.JsonResponseModel
import gparap.apps.quiz.data.QuizModel
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object Utils {
    /**
     * Gets the specific JSON file based on a category name
     */
    fun getJsonFileByCategory(categoryName: String) : String {
        return categoryName.plus(".json")
    }

    /**
     * Gets the QuizModel collection by deserializing a JSON string
     */
    fun getQuizModelFromJSON(jsonString: String): List<QuizModel> {
        val responseModel = Gson().fromJson(jsonString, JsonResponseModel::class.java)
        return responseModel.quiz
    }

    /**
     * Gets from the assets folder the JSON data string of a quiz category
     */
    fun getJSONDataByCategory(context: Context, categoryJson: String): String? {
        val json: String? = try {
            val inputStream: InputStream = context.assets.open(categoryJson)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName(AppConstants.CHARSET_NAME))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}