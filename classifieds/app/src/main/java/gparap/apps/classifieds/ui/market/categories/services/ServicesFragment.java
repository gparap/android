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
package gparap.apps.classifieds.ui.market.categories.services;

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

public class ServicesFragment extends Fragment {

    private ServicesViewModel mViewModel;

    public static ServicesFragment newInstance() {
        return new ServicesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_services, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openServicesCategory(R.id.imageButton_marketCategory_Services_Beauty, "Beauty");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Cleaning, "Cleaning");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Events, "Events");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Other, "Other");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Repair, "Repair");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Tutoring, "Tutoring");
    }

    private void openServicesCategory(int imageResId, String subCategoryName) {
        ImageView imageView = ServicesFragment.this.requireView().findViewById(imageResId);
        imageView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            ServicesFragmentDirections.ActionServicesFragmentToBaseFragment navAction =
                    ServicesFragmentDirections.actionServicesFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Services");
            navAction.setArgsMarketSubCategoryName(subCategoryName);
            navController.navigate(navAction);
        });
    }
}