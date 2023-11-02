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
package gparap.apps.e_commerce.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.badge.ExperimentalBadgeUtils;

import java.util.ArrayList;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.adapters.CategoryAdapter;
import gparap.apps.e_commerce.api.CategoryService;
import gparap.apps.e_commerce.api.HttpClient;
import gparap.apps.e_commerce.data.CartRepository;
import gparap.apps.e_commerce.data.CategoryResponseModel;
import gparap.apps.e_commerce.ui.auth.LoginActivity;
import gparap.apps.e_commerce.utils.AppConstants;
import gparap.apps.e_commerce.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private String username;
    private MaterialToolbar toolbar;
    private BadgeDrawable cartBadge;
    private CartRepository cartRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if user is signed-in proceed or else goto login page
        username = getIntent().getStringExtra(AppConstants.USERNAME);
        if (username == null || username.isEmpty()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        //welcome user
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_welcome_user_after_login) + username, Toast.LENGTH_SHORT).show();

        //setup ActionBar
        toolbar = (MaterialToolbar) Utils.getInstance().setupActionBar(this, R.id.toolbar_main, getString(R.string.text_categories));
        setSupportActionBar(toolbar);

        //create RecyclerView for categories
        RecyclerView categories = findViewById(R.id.recycler_view_categories);

        //set RecyclerView spanCount based on device orientation (Default: PORTRAIT)
        int spanCount = Utils.getInstance().getSpanCount(this.getResources().getConfiguration().orientation);

        //create an adapter for e-commerce categories
        CategoryAdapter adapter = new CategoryAdapter();

        //create an http client
        Retrofit client = new HttpClient().create();

        //create a web service and fetch all e-commerce categories into the adapter
        client.create(CategoryService.class).getAllCategories().enqueue(new Callback<ArrayList<CategoryResponseModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<CategoryResponseModel>> call, @NonNull Response<ArrayList<CategoryResponseModel>> response) {
                adapter.setCategories(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<CategoryResponseModel>> call, @NonNull Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });

        //setup RecyclerView with adapter
        categories.setLayoutManager(new GridLayoutManager(this, spanCount));
        categories.setAdapter(adapter);

        //create a badge with default values for the shopping cart
        cartBadge = Utils.getInstance().createBadge(this);

        //create a cart repository
        cartRepository = new CartRepository(this);
    }

    @Override
    @OptIn(markerClass = ExperimentalBadgeUtils.class)
    protected void onResume() {
        super.onResume();

        //update cart badge with the total number of cart items
        int cartItemsCount = cartRepository.getCartItemsCount();
        cartBadge.setNumber(cartItemsCount);
        BadgeUtils.attachBadgeDrawable(cartBadge, toolbar, R.id.menu_item_cart);
    }

    @Override
    @OptIn(markerClass = ExperimentalBadgeUtils.class)
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        //attach badge to the shopping cart
        BadgeUtils.attachBadgeDrawable(cartBadge, toolbar, R.id.menu_item_cart);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //log user out and redirect to login activity
        if (item.getItemId() == R.id.menu_item_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            Toast.makeText(this, getResources().getString(R.string.text_goodbye_user_after_logout) + username, Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        //display the cart activity
        if (item.getItemId() == R.id.menu_item_cart) {
            startActivity(new Intent(this, CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    public int getCartItemsCount() {
        return cartBadge.getNumber();
    }
}