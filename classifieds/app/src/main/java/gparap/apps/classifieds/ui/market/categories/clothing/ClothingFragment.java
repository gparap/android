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
package gparap.apps.classifieds.ui.market.categories.clothing;

import androidx.lifecycle.ViewModelProvider;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.ui.market.categories.clothing.ClothingFragment;
import gparap.apps.classifieds.ui.market.categories.clothing.ClothingFragment;
import gparap.apps.classifieds.ui.market.categories.clothing.ClothingFragmentDirections;
import gparap.apps.classifieds.utils.Utils;

public class ClothingFragment extends Fragment {

    private ClothingViewModel mViewModel;

    public static ClothingFragment newInstance() {
        return new ClothingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_clothing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadClothingCategoryAssets();
                
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Accessories, "Accessories");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Kids, "Kids");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Men, "Men");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Shoes, "Shoes");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Sportswear, "Sportswear");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Women, "Women");
    }

    private void loadClothingCategoryAssets() {
        String[] assets = new String[]{"accessories.jpg", "kids.jpg", "men.jpg", "shoes.jpg", "sportsware.jpg", "women.jpg"};
        AssetManager assetManager = requireActivity().getAssets();
        String path = "market/categories/clothing";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //Sets the drawables as the content of the ImageViews
        ImageView accessories = ClothingFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Clothing_Accessories);
        accessories.setImageDrawable(drawables.get(0));
        ImageView kids = ClothingFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Clothing_Kids);
        kids.setImageDrawable(drawables.get(1));
        ImageView men = ClothingFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Clothing_Men);
        men.setImageDrawable(drawables.get(2));
        ImageView shoes = ClothingFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Clothing_Shoes);
        shoes.setImageDrawable(drawables.get(3));
        ImageView sportswear = ClothingFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Clothing_Sportswear);
        sportswear.setImageDrawable(drawables.get(4));
        ImageView women = ClothingFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Clothing_Women);
        women.setImageDrawable(drawables.get(5));
    }

    private void openClothingCategory(int imageResId, String subCategoryName) {
        ImageView imageView = ClothingFragment.this.requireView().findViewById(imageResId);
        imageView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            ClothingFragmentDirections.ActionClothingFragmentToBaseFragment navAction =
                    ClothingFragmentDirections.actionClothingFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Clothing");
            navAction.setArgsMarketSubCategoryName(subCategoryName);
            navController.navigate(navAction);
        });
    }
}