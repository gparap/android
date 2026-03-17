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

import gparap.apps.classifieds.R;

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

        //TODO: Refactor
        //------------------------------------------------------------------------------------------
        //open Birds category
        ImageView imageViewBirds = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Birds);
        imageViewBirds.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            AnimalsFragmentDirections.ActionAnimalsFragmentToBaseFragment navAction =
                    AnimalsFragmentDirections.actionAnimalsFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Animals");
            navAction.setArgsMarketSubCategoryName("Birds");
            navController.navigate(navAction);
        });

        //open Cats category
        ImageView imageViewCats = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Cats);
        imageViewCats.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            AnimalsFragmentDirections.ActionAnimalsFragmentToBaseFragment navAction =
                    AnimalsFragmentDirections.actionAnimalsFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Animals");
            navAction.setArgsMarketSubCategoryName("Cats");
            navController.navigate(navAction);
        });

        //open Dogs category
        ImageView imageViewDogs = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Dogs);
        imageViewDogs.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            AnimalsFragmentDirections.ActionAnimalsFragmentToBaseFragment navAction =
                    AnimalsFragmentDirections.actionAnimalsFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Animals");
            navAction.setArgsMarketSubCategoryName("Dogs");
            navController.navigate(navAction);
        });

        //open Fish category
        ImageView imageViewFish = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Fish);
        imageViewFish.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            AnimalsFragmentDirections.ActionAnimalsFragmentToBaseFragment navAction =
                    AnimalsFragmentDirections.actionAnimalsFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Animals");
            navAction.setArgsMarketSubCategoryName("Fish");
            navController.navigate(navAction);
        });

        //open Food category
        ImageView imageViewFood = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Food);
        imageViewFood.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            AnimalsFragmentDirections.ActionAnimalsFragmentToBaseFragment navAction =
                    AnimalsFragmentDirections.actionAnimalsFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Animals");
            navAction.setArgsMarketSubCategoryName("Food");
            navController.navigate(navAction);
        });

        //open Reptiles category
        ImageView imageViewReptiles = AnimalsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Animals_Reptiles);
        imageViewReptiles.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            AnimalsFragmentDirections.ActionAnimalsFragmentToBaseFragment navAction =
                    AnimalsFragmentDirections.actionAnimalsFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Animals");
            navAction.setArgsMarketSubCategoryName("Reptiles");
            navController.navigate(navAction);
        });
        //------------------------------------------------------------------------------------------
    }
}