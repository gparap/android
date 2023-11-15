/*
 * Copyright 2023 gparap
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
package gparap.apps.e_commerce.utils;

public class AppConstants {
    //Web Service constants
    public static final String BASE_URL = "http://10.0.2.2:80/e-commerce/src/services/";
    public static final String IMAGES_URL = "http://10.0.2.2:80/e-commerce/public/img/";
    public static String USERNAME = "username";
    public static String PASSWORD = "password";
    public static String AUTH_STATUS_TRUE = "1";

    //Shared Preferences constants
    public static String SHARED_PREFERENCES = "preferences";
    public static String USER_LOGIN_STATUS = "user_login_status";
    public static String LOGIN_STATUS_0 = "logged_out";
    public static String LOGIN_STATUS_1 = "logged_in";

    //Products constants
    public static final String PRODUCTS_AUTOS_URL = "products/autos/";
    public static final String PRODUCTS_BIKES_URL = "products/bikes/";
    public static final String PRODUCTS_DRINKS_URL = "products/drinks/";
    public static final String PRODUCTS_GARDEN_URL = "products/garden/";
    public static final String PRODUCTS_TECH_URL = "products/tech/";
    public static final String PRODUCTS_GYM_URL = "products/gym/";

    //Intent extras
    public static final String EXTRAS_PRODUCT_DETAILS = "product_details_parcel";

    //Sqlite constants
    public static final int DATABASE_CART_VERSION = 1;
    public static final String DATABASE_CART_NAME = "cart_db";
    public static final String TABLE_NAME_CART_ITEMS = "cart_items";
    public static final String COLUMN_NAME_ITEM_ID = "id";
    public static final String COLUMN_NAME_ITEM_CATEGORY_ID = "category_id";
    public static final String COLUMN_NAME_ITEM_NAME = "name";
    public static final String COLUMN_NAME_ITEM_COST = "cost";
    public static final String COLUMN_NAME_ITEM_DISCOUNT = "discount";
    public static final String COLUMN_NAME_ITEM_IMAGE_URL = "image_url";
    public static final String COLUMN_NAME_ITEM_QUANTITY = "quantity";

    //Other constants
    public static String CATEGORY_ID = "category_id";
    public static int SPAN_COUNT_PORTRAIT = 2;
    public static int SPAN_COUNT_LANDSCAPE = 3;
    public static int PRODUCT_ITEM_STOCK_ZERO = 0;
    public static int PRODUCT_ITEM_STOCK_LOW = 10;
}
