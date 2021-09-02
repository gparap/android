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
package gparap.apps.anagram_color_finder.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import gparap.apps.anagram_color_finder.Colors;

public class Utils {
    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    private Utils() {
    }

    /**
     * Picks a color from the list of colors at random.
     *
     * @return random color
     */
    public String getColorAtRandom() {
        return Colors.colors[new Random().nextInt(Colors.colors.length)];
    }

    /**
     * Forms anagram for a given color.
     *
     * @return anagram
     */
    public String formAnagram(String color) {
        //check if color is more than one word
        StringBuilder stringBuilder = new StringBuilder();
        String[] colorWords = color.split(" ");

        //form anagram for every color word
        for (String colorWord : colorWords) {
            List<String> anagram = Arrays.asList(colorWord.split(""));
            Collections.shuffle(anagram);

            //form anagram
            for (String s : anagram) {
                stringBuilder.append(s);
            }
            stringBuilder.append(" ");
        }
        //trim last space
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        //return anagram
        return stringBuilder.toString();
    }
}
