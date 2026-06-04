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
package gparap.apps.classifieds.ui.market.categories.electronics;

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
import gparap.apps.classifieds.ui.market.categories.clothing.ClothingFragment;
import gparap.apps.classifieds.utils.Utils;

public class ElectronicsFragment extends Fragment {

    private ElectronicsViewModel mViewModel;

    public static ElectronicsFragment newInstance() {
        return new ElectronicsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_electronics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadElectronicsCategoryAssets();

        openElectronicsCategory(R.id.imageButton_marketCategory_Electronics_Audio, "Audio");
        openElectronicsCategory(R.id.imageButton_marketCategory_Electronics_Cameras, "Cameras");
        openElectronicsCategory(R.id.imageButton_marketCategory_Electronics_Computers, "Computers");
        openElectronicsCategory(R.id.imageButton_marketCategory_Electronics_Gadgets, "Gadgets");
        openElectronicsCategory(R.id.imageButton_marketCategory_Electronics_Mobiles, "Mobiles");
        openElectronicsCategory(R.id.imageButton_marketCategory_Electronics_tvs, "TVs");
    }

    private void loadElectronicsCategoryAssets() {
        String[] assets = new String[]{"audio.jpg", "cameras.jpg", "computers.jpg", "gadgets.jpg", "mobiles.jpg", "tvs.jpg"};
        AssetManager assetManager = requireActivity().getAssets();
        String path = "market/categories/electronics";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //Sets the drawables as the content of the ImageViews
        ImageView audio = ElectronicsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Electronics_Audio);
        audio.setImageDrawable(drawables.get(0));
        ImageView cameras = ElectronicsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Electronics_Cameras);
        cameras.setImageDrawable(drawables.get(1));
        ImageView computers = ElectronicsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Electronics_Computers);
        computers.setImageDrawable(drawables.get(2));
        ImageView gadgets = ElectronicsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Electronics_Gadgets);
        gadgets.setImageDrawable(drawables.get(3));
        ImageView mobiles = ElectronicsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Electronics_Mobiles);
        mobiles.setImageDrawable(drawables.get(4));
        ImageView tvs = ElectronicsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Electronics_tvs);
        tvs.setImageDrawable(drawables.get(5));
    }

    private void openElectronicsCategory(int imageResId, String subCategoryName) {
        ImageView imageView = ElectronicsFragment.this.requireView().findViewById(imageResId);
        imageView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            ElectronicsFragmentDirections.ActionElectronicsFragmentToBaseFragment navAction =
                    ElectronicsFragmentDirections.actionElectronicsFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Electronics");
            navAction.setArgsMarketSubCategoryName(subCategoryName);
            navController.navigate(navAction);
        });
    }
}