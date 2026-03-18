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

        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Accessories, "Accessories");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Bicycles, "Bicycles");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Bikes, "Bikes");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Cars, "Cars");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Parts, "Parts");
        openAutomobilesCategory(R.id.imageButton_marketCategory_Automobiles_Utility, "Utility");
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