/*
 * Copyright 2026 gparap
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
package gparap.apps.classifieds.utils;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.util.ArrayList;

public class Utils {
    private static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    public static Utils getInstance() {
        return INSTANCE;
    }

    /**
     * Creates image drawables from the application's raw asset images.
     * @param assetManager the access to an application's raw asset files
     * @param assets an array of raw asset images' names
     * @param path the path to the assets
     * @return a collection of drawables
     */
    public ArrayList<Drawable> getDrawablesFromAssets(android.content.res.AssetManager assetManager, String[] assets, String path) {
        ArrayList<Drawable> drawables = new ArrayList<>();
        for (String asset : assets) {
            try {
                InputStream stream = assetManager.open(path + "/" + asset);
                Drawable drawable = Drawable.createFromStream(stream, null);
                drawables.add(drawable);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return drawables;
    }
}
