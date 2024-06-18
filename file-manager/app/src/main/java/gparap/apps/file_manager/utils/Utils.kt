/*
 * Copyright 2024 gparap
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
package gparap.apps.file_manager.utils

object Utils {
    /** Checks if it is the android data directory. */
    fun isSystemDirectory(pathname: String): Boolean {
        return pathname.contains(".*/data$".toRegex()) || pathname.contains(".*/data/.*".toRegex())
    }

    /** Checks if it is a hidden file. */
    fun isHiddenFile(pathname: String): Boolean {
        //exclude hidden and database files
        return (pathname.matches("^\\..+".toRegex()))
    }

    /** Checks if it is a hidden file. */
    fun isDatabaseFile(pathname: String): Boolean {
        //exclude hidden and database files
        return (pathname.contains("\\.db(\$|-.+)$".toRegex()))
    }
}