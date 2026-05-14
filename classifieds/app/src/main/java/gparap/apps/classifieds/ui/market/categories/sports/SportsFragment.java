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

        openSportsCategory(R.id.imageButton_marketCategory_Sports_Apparel, "Apparel");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Equipment, "Equipment");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Fitness, "Fitness");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Indoors, "Indoors");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Other, "Other");
        openSportsCategory(R.id.imageButton_marketCategory_Sports_Outdoors, "Outdoors");
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