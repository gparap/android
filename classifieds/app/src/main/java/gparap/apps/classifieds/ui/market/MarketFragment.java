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
package gparap.apps.classifieds.ui.market;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import gparap.apps.classifieds.databinding.FragmentMarketBinding;

public class MarketFragment extends Fragment {
    NavController navController;

    private FragmentMarketBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MarketViewModel viewModel = new ViewModelProvider(this).get(MarketViewModel.class);

        binding = FragmentMarketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        navController = Navigation.findNavController(container);

        openMarketCategory();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openMarketCategory() {
        //open Animals category
        ImageView animals = binding.imageViewMarketCategoryAnimals;
        animals.setOnClickListener(v -> findNavController(v).navigate(MarketFragmentDirections.actionNavigationMarketToAnimalsFragment()));
        //open Automobiles category
        ImageView automobiles = binding.imageViewMarketCategoryAutomobiles;
        automobiles.setOnClickListener(v -> findNavController(v).navigate(MarketFragmentDirections.actionNavigationMarketToAutomobilesFragment()));
        //open Clothing category
        ImageView clothing = binding.imageViewMarketCategoryClothing;
        clothing.setOnClickListener(v -> findNavController(v).navigate(MarketFragmentDirections.actionNavigationMarketToClothingFragment()));
        //open Electronics category
        ImageView electronics = binding.imageViewMarketCategoryElectronics;
        electronics.setOnClickListener(v -> findNavController(v).navigate(MarketFragmentDirections.actionNavigationMarketToElectronicsFragment()));
        //open employment category
        ImageView employment = binding.imageViewMarketCategoryEmployment;
        employment.setOnClickListener(v -> findNavController(v).navigate(MarketFragmentDirections.actionNavigationMarketToEmploymentFragment()));
        //open Home category
        ImageView home = binding.imageViewMarketCategoryHome;
        home.setOnClickListener(v -> findNavController(v).navigate(MarketFragmentDirections.actionNavigationMarketToHomeFragment()));
        //open Property category
        ImageView property = binding.imageViewMarketCategoryProperty;
        property.setOnClickListener(v -> findNavController(v).navigate(MarketFragmentDirections.actionNavigationMarketToPropertyFragment()));
        //open Services category
        ImageView services = binding.imageViewMarketCategoryServices;
        services.setOnClickListener(v -> findNavController(v).navigate(MarketFragmentDirections.actionNavigationMarketToServicesFragment()));
        //open Sports category
        ImageView sports = binding.imageViewMarketCategorySports;
        sports.setOnClickListener(v -> findNavController(v).navigate(MarketFragmentDirections.actionNavigationMarketToSportsFragment()));
    }
}