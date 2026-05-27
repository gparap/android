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
package gparap.apps.classifieds.ui.market.categories.animals;

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

public class AnimalsFragment extends Fragment {

    private AnimalsViewModel mViewModel;

    public static AnimalsFragment newInstance() {
        return new AnimalsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_category_animals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadAnimalCategoryAssets();

        openAnimalCategory(R.id.imageButton_marketCategory_Animals_Birds, "Birds");
        openAnimalCategory(R.id.imageButton_marketCategory_Animals_Cats, "Cats");
        openAnimalCategory(R.id.imageButton_marketCategory_Animals_Dogs, "Dogs");
        openAnimalCategory(R.id.imageButton_marketCategory_Animals_Fish, "Fish");
        openAnimalCategory(R.id.imageButton_marketCategory_Animals_Food, "Food");
        openAnimalCategory(R.id.imageButton_marketCategory_Animals_Reptiles, "Reptiles");
    }

    private void loadAnimalCategoryAssets() {
        String[] assets = new String[]{"birds.jpg", "cats.jpg", "dogs.jpg", "fish.jpg", "food.jpg", "reptiles.jpg"};
        AssetManager assetManager = requireActivity().getAssets();
        String path = "market/categories/animals";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //Sets the drawables as the content of the ImageViews
        ImageView birds = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Birds);
        birds.setImageDrawable(drawables.get(0));
        ImageView cats = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Cats);
        cats.setImageDrawable(drawables.get(1));
        ImageView dogs = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Dogs);
        dogs.setImageDrawable(drawables.get(2));
        ImageView fish = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Fish);
        fish.setImageDrawable(drawables.get(3));
        ImageView food = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Food);
        food.setImageDrawable(drawables.get(4));
        ImageView reptiles = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Reptiles);
        reptiles.setImageDrawable(drawables.get(5));
    }

    private void openAnimalCategory(int imageResId, String subCategoryName) {
        ImageView imageView = AnimalsFragment.this.requireView().findViewById(imageResId);
        imageView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            AnimalsFragmentDirections.ActionAnimalsFragmentToBaseFragment navAction =
                    AnimalsFragmentDirections.actionAnimalsFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Animals");
            navAction.setArgsMarketSubCategoryName(subCategoryName);
            navController.navigate(navAction);
        });
    }
}