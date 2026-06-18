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
package gparap.apps.classifieds.ui.market.categories.sports;

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
import gparap.apps.classifieds.ui.market.categories.sports.SportsFragment;
import gparap.apps.classifieds.utils.Utils;

public class SportsFragment extends Fragment {

    private SportsViewModel mViewModel;

    public static SportsFragment newInstance() {
        return new SportsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_sports, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadSportsCategoryAssets();

        openSportsCategory(R.id.imageButton_marketCategory_Sports_Apparel, "Apparel");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Equipment, "Equipment");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Fitness, "Fitness");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Indoors, "Indoors");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Other, "Other");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Outdoors, "Outdoors");
    }

    private void loadSportsCategoryAssets() {
        String[] assets = new String[]{"apparel.jpg", "equipment.jpg", "fitness.jpg", "indoors.jpg",
                "other.jpg", "outdoors.jpg"};
        AssetManager assetManager = requireActivity().getAssets();
        String path = "market/categories/sports";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //Sets the drawables as the content of the ImageViews
        ImageView apparel = SportsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Sports_Apparel);
        apparel.setImageDrawable(drawables.get(0));
        ImageView equipment = SportsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Sports_Equipment);
        equipment.setImageDrawable(drawables.get(1));
        ImageView fitness = SportsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Sports_Fitness);
        fitness.setImageDrawable(drawables.get(2));
        ImageView indoors = SportsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Sports_Indoors);
        indoors.setImageDrawable(drawables.get(3));
        ImageView other = SportsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Sports_Other);
        other.setImageDrawable(drawables.get(4));
        ImageView outdoors = SportsFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Sports_Outdoors);
        outdoors.setImageDrawable(drawables.get(5));
    }

    private void openSportsCategory(int imageResId, String subCategoryName) {
        ImageView imageView = SportsFragment.this.requireView().findViewById(imageResId);
        imageView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            SportsFragmentDirections.ActionSportsFragmentToBaseFragment navAction =
                    SportsFragmentDirections.actionSportsFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Sports");
            navAction.setArgsMarketSubCategoryName(subCategoryName);
            navController.navigate(navAction);
        });
    }
}