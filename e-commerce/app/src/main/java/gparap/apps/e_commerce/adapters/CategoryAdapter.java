/*
 * Copyright 2022 gparap
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.data.CategoryResponseModel;
import gparap.apps.e_commerce.ui.ProductActivity;
import gparap.apps.e_commerce.utils.AppConstants;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private ArrayList<CategoryResponseModel> categories = new ArrayList<>();
    private Context context;

    @SuppressWarnings("unused")
    public ArrayList<CategoryResponseModel> getCategories() {
        return categories;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCategories(ArrayList<CategoryResponseModel> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get context
        context = parent.getContext();

        //get layout inflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //create item view
        View view = inflater.inflate(R.layout.cardview_category, parent, false);

        //create the view holder for this item view
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        //create the image Uri
        String uriString = AppConstants.IMAGES_URL + categories.get(position).getUrl();
        Uri uri = Uri.parse(uriString);

        //set the category image
        Picasso.get().load(uri).into(holder.imageView);

        //show products of this category in another activity
        holder.imageView.setOnClickListener(view->{
            Intent intent = new Intent(context, ProductActivity.class);
            intent.putExtra(AppConstants.CATEGORY_ID, categories.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_category_main);
        }
    }
}
