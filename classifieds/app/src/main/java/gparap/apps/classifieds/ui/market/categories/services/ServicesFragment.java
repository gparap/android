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

        loadServicesCategoryAssets();

        openServicesCategory(R.id.imageButton_marketCategory_Services_Beauty, "Beauty");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Cleaning, "Cleaning");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Events, "Events");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Other, "Other");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Repair, "Repair");
        openServicesCategory(R.id.imageButton_marketCategory_Services_Tutoring, "Tutoring");
    }

    private void loadServicesCategoryAssets() {
        String[] assets = new String[]{"beauty.jpg", "cleaning.jpg", "events.jpg", "other.jpg", "repair.jpg", "tutoring.jpg"};
        AssetManager assetManager = requireActivity().getAssets();
        String path = "market/categories/services";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //Sets the drawables as the content of the ImageViews
        ImageView beauty = ServicesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Services_Beauty);
        beauty.setImageDrawable(drawables.get(0));
        ImageView cleaning = ServicesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Services_Cleaning);
        cleaning.setImageDrawable(drawables.get(1));
        ImageView events = ServicesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Services_Events);
        events.setImageDrawable(drawables.get(2));
        ImageView other = ServicesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Services_Other);
        other.setImageDrawable(drawables.get(3));
        ImageView repair = ServicesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Services_Repair);
        repair.setImageDrawable(drawables.get(4));
        ImageView tutoring = ServicesFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Services_Tutoring);
        tutoring.setImageDrawable(drawables.get(5));
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