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

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class LocaleUtilsUnitTest {
    @Test
    public void getSupportedLanguage_German() {
        Locale.setDefault(new Locale("de", "non-tested"));
        String expected = "de";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Greek() {
        Locale.setDefault(new Locale("el", "non-tested"));
        String expected = "el";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_France() {
        Locale.setDefault(new Locale("fr", "non-tested"));
        String expected = "fr";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Hindi() {
        Locale.setDefault(new Locale("hi", "non-tested"));
        String expected = "hi";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Indonesian() {
        Locale.setDefault(new Locale("in", "non-tested"));
        String expected = "id";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Italian() {
        Locale.setDefault(new Locale("it", "non-tested"));
        String expected = "it";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Japanese() {
        Locale.setDefault(new Locale("ja", "non-tested"));
        String expected = "ja";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Korean() {
        Locale.setDefault(new Locale("ko", "non-tested"));
        String expected = "ko";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Portuguese() {
        Locale.setDefault(new Locale("pt", "non-tested"));
        String expected = "pt";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Russian() {
        Locale.setDefault(new Locale("ru", "non-tested"));
        String expected = "ru";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Thai() {
        Locale.setDefault(new Locale("th", "non-tested"));
        String expected = "th";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_Vietnamese() {
        Locale.setDefault(new Locale("vi", "non-tested"));
        String expected = "vi";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }

    @Test
    public void getSupportedLanguage_traditionalChinese() {
        Locale.setDefault(new Locale("zh_TW", "non-tested"));
        String expected = "zh_tw";
        String actual = LocaleUtils.getInstance().getSupportedLanguage();
        assertEquals(expected, actual);
    }
}