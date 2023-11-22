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
package gparap.apps.e_commerce.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.data.ProductResponseModel;
import gparap.apps.e_commerce.ui.ProductDetailsActivity;
import gparap.apps.e_commerce.utils.AppConstants;
import gparap.apps.e_commerce.utils.Utils;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<ProductResponseModel> products = new ArrayList<>();
    private Context context;

    @SuppressWarnings("unused")
    public ArrayList<ProductResponseModel> getCategories() {
        return products;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setProducts(ArrayList<ProductResponseModel> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get target context
        context = parent.getContext();

        //get layout inflater
        LayoutInflater inflater = LayoutInflater.from(context);

        //create item view
        View view = inflater.inflate(R.layout.cardview_product, parent, false);

        //create the view holder for this item view
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        //create the image Uri prefix based on category
        String uriPrefix = Utils.getInstance().createUriPrefix(products.get(position).getCategoryId());

        //create the image Uri
        Uri uri = Uri.parse(uriPrefix + products.get(position).getUrl());

        //set the product image
        Picasso.get().load(uri).into(holder.imageView);

        //set the product name
        holder.textView.setText(products.get(position).getName());

        //open product details
        holder.imageView.setOnClickListener(view->{
            //add product to a parcelable array to be used as intent extra
            ArrayList<ProductResponseModel> models = new ArrayList<>();
            models.add(products.get(position));

            //start an intent for ProductDetailsActivity with extras
            Intent intent = new Intent(context, ProductDetailsActivity.class);
            intent.putParcelableArrayListExtra(AppConstants.EXTRAS_PRODUCT_DETAILS, models);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_product);
            textView = itemView.findViewById(R.id.text_view_product);
        }
    }
}
