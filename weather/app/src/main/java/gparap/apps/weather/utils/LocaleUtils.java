/*
 * Copyright 2021 gparap
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
package gparap.apps.weather.utils;

import java.util.ArrayList;
import java.util.Locale;

public class LocaleUtils {
    private static LocaleUtils instance;
    private static ArrayList<String> locales;

    private LocaleUtils() {

    }

    public static LocaleUtils getInstance() {
        if (instance == null) {
            instance = new LocaleUtils();
            initSupportedLocales();
        }
        return instance;
    }

    private static void initSupportedLocales() {
        locales = new ArrayList<>();
        locales.add("en");  //English (default)
        locales.add("de");  //German
        locales.add("el");  //Greek
        locales.add("fr");  //France
        locales.add("hi");  //Hindi
        locales.add("in");  //Indonesian
        locales.add("it");  //Italian
        locales.add("ja");  //Japanese
        locales.add("ko");  //Korean
        locales.add("pt");  //Portuguese
        locales.add("ru");  //Russian
        locales.add("th");  //Thai
        locales.add("vi");  //Vietnamese
        locales.add("zh");  //Chinese (traditional)
    }

    /**
     * Decides whether the provider's api parameter "lang" should include
     * the user's or the default (en) locale when requesting weather data.
     * This decision is based on provider's api language support and app internationalization.
     *
     * @return "lang" param for api call
     */
    public String getSupportedLanguage() {
        //get default locale
        String locale = Locale.getDefault().toString().substring(0, 2);

        //check if locale should be supported
        if (locales.contains(locale)) {
            //special cases
            if (locale.equals("in")) {  //Indonesian (android api)
                return "id";            //Indonesian (weather api)
            }
            if (locale.equals("zh")) {  //Chinese (traditional)
                return "zh_tw";
            }
            return locale;
        }

        //return default locale
        return locales.get(0);
    }
}
