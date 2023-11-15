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

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.data.CartItemModel;
import gparap.apps.e_commerce.data.CartRepository;
import gparap.apps.e_commerce.data.ProductResponseModel;
import gparap.apps.e_commerce.utils.AppConstants;
import gparap.apps.e_commerce.utils.Utils;

public class ProductDetailsActivity extends AppCompatActivity {
    private CartRepository cartRepository;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Utils.getInstance().setupActionBar(this, R.id.toolbar_product_details, "");

        //get intent with product details
        ArrayList<ProductResponseModel> productDetails = getIntent().getParcelableArrayListExtra(AppConstants.EXTRAS_PRODUCT_DETAILS);

        //update AppBar title with product name
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(productDetails.get(0).getName());
        }

        //create the image Uri prefix based on category
        String uriPrefix = Utils.getInstance().createUriPrefix(productDetails.get(0).getCategoryId());

        //create the image Uri
        Uri uri = Uri.parse(uriPrefix + productDetails.get(0).getUrl());

        //set the product image
        ImageView imageViewProduct = findViewById(R.id.image_view_product_details);
        Picasso.get().load(uri).into(imageViewProduct);

        //set product's price
        TextView productPrice = findViewById(R.id.text_view_price_product_details);
        productPrice.setText(
                getResources().getString(R.string.price_prefix) +
                productDetails.get(0).getPrice().toString() +
                getResources().getString(R.string.price_suffix)
        );

        //show the product discount, if exists
        if (productDetails.get(0).getDiscount() != 0) {
            TextView productDiscount = findViewById(R.id.text_view_discount_product_details);
            productDiscount.setVisibility(View.VISIBLE);
            productDiscount.setText(getResources().getString(R.string.discount_prefix)
                    + productDetails.get(0).getDiscount()
                    + getResources().getString(R.string.discount_suffix)
            );
        }

        //show product availability based on items left in stock
        TextView productStock = findViewById(R.id.text_view_stock_product_details);
        productStock.setText(Utils.getInstance().getProductAvailability(
                productDetails.get(0).getStock(), getResources()
        ));

        //update product's description
        TextView productDescription = findViewById(R.id.text_view_description_product_details);
        productDescription.setText(productDetails.get(0).getDescription());

        //add to cart
        FloatingActionButton fabAddToCart = findViewById(R.id.fab_add_to_cart_product_details);
        fabAddToCart.setOnClickListener(v -> {
            //display a confirmation dialog
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.text_add_to_cart_title))
                    .setMessage(getString(R.string.text_add_to_cart_message))
                    .setPositiveButton(getString(R.string.text_dialog_ok), (dialog, which) -> {
                        //create a cart repository
                        cartRepository = new CartRepository(this);

                        //create a card item from selected product
                        CartItemModel cartItem = new CartItemModel(
                                productDetails.get(0).getId(),
                                productDetails.get(0).getCategoryId(),
                                productDetails.get(0).getName(),
                                productDetails.get(0).getPrice(),
                                productDetails.get(0).getDiscount(),
                                productDetails.get(0).getUrl(),
                                1
                        );

                        //add the selected item to cart and get the transaction result
                        long transactionResult = cartRepository.addItemToCart(cartItem);

                        //display cart message to the user
                        if (transactionResult == -1L) {
                            Toast.makeText(this, getString(R.string.toast_add_to_cart_failure), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, getString(R.string.toast_add_to_cart_success), Toast.LENGTH_SHORT).show();
                        }

                        //close the alert dialog and continue shopping in category
                        dialog.dismiss();
                        finish();
                    })
                    .setNegativeButton(getString(R.string.text_dialog_cancel), (dialog, which) -> dialog.dismiss())
                    .create();

            //show the alert dialog
            alertDialog.show();
        });
    }

    @Override
    protected void onDestroy() {
        try {
            cartRepository.closeDatabase();
        }catch (Exception ignored){}
        super.onDestroy();
    }
}