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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.adapters.CartItemAdapter;
import gparap.apps.e_commerce.data.CartItemModel;
import gparap.apps.e_commerce.data.CartRepository;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //fetch cart items from the database
        CartRepository cartRepository = new CartRepository(this);
        ArrayList<CartItemModel> cartItemsList;
        cartItemsList = cartRepository.getCartItems();

        //setup recycler view with adapter for cart items
        RecyclerView cartItems = findViewById(R.id.recycler_view_cart);
        cartItems.setLayoutManager(new LinearLayoutManager(this));
        CartItemAdapter adapter = new CartItemAdapter();
        adapter.setCartItems(cartItemsList);
        cartItems.setAdapter(adapter);
    }
}
