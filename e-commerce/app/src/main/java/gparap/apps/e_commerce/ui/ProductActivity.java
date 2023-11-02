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

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.adapters.ProductAdapter;
import gparap.apps.e_commerce.api.HttpClient;
import gparap.apps.e_commerce.api.ProductService;
import gparap.apps.e_commerce.data.ProductResponseModel;
import gparap.apps.e_commerce.utils.AppConstants;
import gparap.apps.e_commerce.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Utils.getInstance().setupActionBar(this, R.id.toolbar_products, getString(R.string.text_products));

        //start progress loading
        ProgressBar progressBar = findViewById(R.id.progress_products);
        progressBar.setVisibility(View.VISIBLE);

        //get category id from intent
        int categoryId = getIntent().getIntExtra(AppConstants.CATEGORY_ID, 0);

        //create an adapter for products
        ProductAdapter adapter = new ProductAdapter();

        //create http client
        Retrofit httpClient = new HttpClient().create();

        //create web service
        ProductService webService = httpClient.create(ProductService.class);

        //consume web service to fetch all products by category id
        webService.getAllProductsByCategoryId(String.valueOf(categoryId))
                .enqueue(new Callback<ArrayList<ProductResponseModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ProductResponseModel>> call, @NonNull Response<ArrayList<ProductResponseModel>> response) {
                //add products to adapter
                ArrayList<ProductResponseModel> products = response.body();
                adapter.setProducts(products);

                //hide progress bar
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ProductResponseModel>> call, @NonNull Throwable t) {
                //DEBUG
                System.out.println(t.getLocalizedMessage());

                //hide progress bar
                progressBar.setVisibility(View.GONE);
            }
        });

        //create RecyclerView for products
        RecyclerView products = findViewById(R.id.recycler_view_products);

        //set RecyclerView spanCount based on device orientation (Default: PORTRAIT)
        int spanCount = Utils.getInstance().getSpanCount(this.getResources().getConfiguration().orientation);

        //setup RecyclerView with adapter
        products.setLayoutManager(new GridLayoutManager(this, spanCount));
        products.setAdapter(adapter);
    }
}