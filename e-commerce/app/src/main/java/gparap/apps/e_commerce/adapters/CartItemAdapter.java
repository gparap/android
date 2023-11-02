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
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gparap.apps.e_commerce.R;
import gparap.apps.e_commerce.data.CartItemModel;
import gparap.apps.e_commerce.data.CartRepository;
import gparap.apps.e_commerce.utils.AppConstants;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {
    private ArrayList<CartItemModel> cartItems = new ArrayList<>();
    private Context context;
    private CartRepository cartRepository;

    @SuppressWarnings("unused")
    public ArrayList<CartItemModel> getCartItems() {
        return cartItems;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCartItems(ArrayList<CartItemModel> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get context
        context = parent.getContext();

        //initialize repository
        cartRepository = new CartRepository(context);

        //get layout inflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //create item view
        View view = inflater.inflate(R.layout.cardview_cart_item, parent, false);

        //create the view holder for this item view
        return new CartItemViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        //get the cart item's image path from its category id
        int categoryId = cartItems.get(position).getCategoryId();
        String itemImagePath = "";
        switch (categoryId) {
            case 1: itemImagePath = AppConstants.PRODUCTS_AUTOS_URL; break;
            case 2: itemImagePath = AppConstants.PRODUCTS_BIKES_URL; break;
            case 3: itemImagePath = AppConstants.PRODUCTS_DRINKS_URL; break;
            case 4: itemImagePath = AppConstants.PRODUCTS_GARDEN_URL; break;
            case 5: itemImagePath = AppConstants.PRODUCTS_GYM_URL; break;
            case 6: itemImagePath = AppConstants.PRODUCTS_TECH_URL; break;
        }

        //create the image URI reference
        String uriString = AppConstants.IMAGES_URL + itemImagePath + cartItems.get(position).getImageUrl();
        Uri uri = Uri.parse(uriString);

        //set the cart item image
        Picasso.get().load(uri).into(holder.imageView);

        //show the cart item name
        holder.name.setText(cartItems.get(position).getName());

        //show the cart item price
        holder.price.setText(context.getResources().getString(R.string.price_prefix) +
                cartItems.get(position).getPrice() +
                context.getResources().getString(R.string.price_suffix));

        //show the cart item quantity
        holder.quantity.setText(String.valueOf(cartItems.get(position).getQuantity()));

        //remove item from the cart
        holder.buttonRemoveItem.setOnClickListener(v -> {
            //create an alert dialog to warn the user before removing the item from cart
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                    .setTitle(context.getResources().getString(R.string.text_remove_from_cart_title))
                    .setMessage(context.getResources().getString(R.string.text_remove_from_cart_message))
                    .setPositiveButton(context.getResources().getString(R.string.text_dialog_ok), (dialog, which) -> {
                        cartRepository.removeCartItem(cartItems.get(position).getId());
                        cartItems.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton(context.getResources().getString(R.string.text_dialog_cancel), (dialog, which) ->
                            dialog.dismiss());
            //display the dialog
            alertDialog.show();
        });

        //increase cart item quantity
        holder.buttonAddOneItem.setOnClickListener(v -> {
            //get the existing quantity & add one
            int quantity = cartItems.get(position).getQuantity();
            quantity += 1;

            //update the text field
            holder.quantity.setText(String.valueOf(quantity));

            //update the cart database
            cartRepository.updateCartItemQuantity(cartItems.get(position).getId(), quantity);

            //update the adapter
            cartItems.get(position).setQuantity(quantity);
        });

        //decrease cart item quantity
        holder.buttonRemoveOneItem.setOnClickListener(v -> {
            //get the existing quantity & remove one
            int quantity = cartItems.get(position).getQuantity();
            quantity -= 1;
            if (quantity < 0){
                quantity = 0;
            }

            //update the text field
            holder.quantity.setText(String.valueOf(quantity));

            //update the cart database
            cartRepository.updateCartItemQuantity(cartItems.get(position).getId(), quantity);

            //update the adapter
            cartItems.get(position).setQuantity(quantity);
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final ImageView imageView;
        private final TextView quantity;
        private final ImageButton buttonRemoveItem;
        private final ImageButton buttonAddOneItem;
        private final ImageButton buttonRemoveOneItem;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_view_cart_item_name);
            price = itemView.findViewById(R.id.text_view_cart_item_price);
            imageView = itemView.findViewById(R.id.image_view_cart_item);
            quantity = itemView.findViewById(R.id.text_view_cart_item_quantity);
            buttonRemoveItem = itemView.findViewById(R.id.button_cart_remove_item);
            buttonAddOneItem = itemView.findViewById(R.id.button_cart_add_one);
            buttonRemoveOneItem = itemView.findViewById(R.id.button_cart_remove_one);
        }
    }
}
