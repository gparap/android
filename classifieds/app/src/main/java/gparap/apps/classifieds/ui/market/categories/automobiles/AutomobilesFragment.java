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
package gparap.apps.classifieds.ui.market.categories.automobiles;

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

public class AutomobilesFragment extends Fragment {

    private AutomobilesViewModel mViewModel;

    public static AutomobilesFragment newInstance() {
        return new AutomobilesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_automobiles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadAutomobilesCategoryAssets();

        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Accessories, "Accessories");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Bicycles, "Bicycles");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Bikes, "Bikes");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Cars, "Cars");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Parts, "Parts");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Utility, "Utility");
    }

    private void loadAutomobilesCategoryAssets() {
        String[] assets = new String[]{"accessories.jpg", "bicycles.jpg", "bikes.jpg", "cars.jpg", "parts.jpg", "utility.jpg"};
        AssetManager assetManager = requireActivity().getAssets();
        String path = "market/categories/automobiles";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //Sets the drawables as the content of the ImageViews
        ImageView accessories = AutomobilesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Automobiles_Accessories);
        accessories.setImageDrawable(drawables.get(0));
        ImageView bicycles = AutomobilesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Automobiles_Bicycles);
        bicycles.setImageDrawable(drawables.get(1));
        ImageView bikes = AutomobilesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Automobiles_Bikes);
        bikes.setImageDrawable(drawables.get(2));
        ImageView cars = AutomobilesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Automobiles_Cars);
        cars.setImageDrawable(drawables.get(3));
        ImageView parts = AutomobilesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Automobiles_Parts);
        parts.setImageDrawable(drawables.get(4));
        ImageView utility = AutomobilesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Automobiles_Utility);
        utility.setImageDrawable(drawables.get(5));
    }

    private void openAutomobilesCategory(int imageResId, String subCategoryName) {
        ImageView imageView = AutomobilesFragment.this.requireView().findViewById(imageResId);
        imageView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            AutomobilesFragmentDirections.ActionAutomobilesFragmentToBaseFragment navAction =
                    AutomobilesFragmentDirections.actionAutomobilesFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Automobiles");
            navAction.setArgsMarketSubCategoryName(subCategoryName);
            navController.navigate(navAction);
        });
    }
}