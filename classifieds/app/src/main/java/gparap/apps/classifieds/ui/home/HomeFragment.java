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
package gparap.apps.classifieds.ui.market.categories.home;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.utils.Utils;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadHomeCategoryAssets();

        openHomeCategory(R.id.imageButton_marketCategory_Home_Appliances, "Appliances");
        openHomeCategory(R.id.imageButton_marketCategory_Home_Decor, "Decor");
        openHomeCategory(R.id.imageButton_marketCategory_Home_Furniture, "Furniture");
        openHomeCategory(R.id.imageButton_marketCategory_Home_Garden, "Garden");
        openHomeCategory(R.id.imageButton_marketCategory_Home_Kitchen, "Kitchen");
        openHomeCategory(R.id.imageButton_marketCategory_Home_Other, "Other");
    }

    private void loadHomeCategoryAssets() {
        String[] assets = new String[]{"appliances.jpg", "decor.jpg", "furniture.jpg", "garden.jpg",
                "kitchen.jpg", "other.jpg"};
        AssetManager assetManager = requireActivity().getAssets();
        String path = "market/categories/home";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //Sets the drawables as the content of the ImageViews
        ImageView appliances = HomeFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Home_Appliances);
        appliances.setImageDrawable(drawables.get(0));
        ImageView decor = HomeFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Home_Decor);
        decor.setImageDrawable(drawables.get(1));
        ImageView furniture = HomeFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Home_Furniture);
        furniture.setImageDrawable(drawables.get(2));
        ImageView garden = HomeFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Home_Garden);
        garden.setImageDrawable(drawables.get(3));
        ImageView kitchen = HomeFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Home_Kitchen);
        kitchen.setImageDrawable(drawables.get(4));
        ImageView other = HomeFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Home_Other);
        other.setImageDrawable(drawables.get(5));
    }

    private void openHomeCategory(int imageResId, String subCategoryName) {
        ImageView imageView = HomeFragment.this.requireView().findViewById(imageResId);
        imageView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            HomeFragmentDirections.ActionHomeFragmentToBaseFragment navAction =
                    HomeFragmentDirections.actionHomeFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Home");
            navAction.setArgsMarketSubCategoryName(subCategoryName);
            navController.navigate(navAction);
        });
    }
}
