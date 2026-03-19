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

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.ui.market.categories.clothing.ClothingFragment;
import gparap.apps.classifieds.ui.market.categories.clothing.ClothingFragmentDirections;

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

        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Accessories, "Accessories");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Kids, "Kids");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Men, "Men");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Shoes, "Shoes");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Sportswear, "Sportswear");
        openClothingCategory(R.id.imageButton_marketCategory_Clothing_Women, "Women");
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