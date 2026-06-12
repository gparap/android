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
package gparap.apps.classifieds.ui.market.categories.property;

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
import gparap.apps.classifieds.ui.market.categories.property.PropertyFragment;
import gparap.apps.classifieds.utils.Utils;

public class PropertyFragment extends Fragment {

    private PropertyViewModel mViewModel;

    public static PropertyFragment newInstance() {
        return new PropertyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_property, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadPropertyCategoryAssets();

        openPropertyCategory(R.id.imageButton_marketCategory_Property_Commercial, "Commercial");
        openPropertyCategory(R.id.imageButton_marketCategory_Property_Guest, "Guest");
        openPropertyCategory(R.id.imageButton_marketCategory_Property_Land, "Land");
        openPropertyCategory(R.id.imageButton_marketCategory_Property_Rent, "Rent");
        openPropertyCategory(R.id.imageButton_marketCategory_Property_Sale, "Sale");
        openPropertyCategory(R.id.imageButton_marketCategory_Property_Other, "Other");
    }

    private void loadPropertyCategoryAssets() {
        String[] assets = new String[]{"commercial.jpg", "guest.jpg", "land.jpg", "other.jpg", "rent.jpg", "sale.jpg"};
        AssetManager assetManager = requireActivity().getAssets();
        String path = "market/categories/property";
        ArrayList<Drawable> drawables = Utils.getInstance().getDrawablesFromAssets(assetManager, assets, path);

        //Sets the drawables as the content of the ImageViews
        ImageView commercial = PropertyFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Property_Commercial);
        commercial.setImageDrawable(drawables.get(0));
        ImageView guest = PropertyFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Property_Guest);
        guest.setImageDrawable(drawables.get(1));
        ImageView land = PropertyFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Property_Land);
        land.setImageDrawable(drawables.get(2));
        ImageView other = PropertyFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Property_Other);
        other.setImageDrawable(drawables.get(3));
        ImageView rent = PropertyFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Property_Rent);
        rent.setImageDrawable(drawables.get(4));
        ImageView sale = PropertyFragment.this.requireView().findViewById(R.id.imageButton_marketCategory_Property_Sale);
        sale.setImageDrawable(drawables.get(5));
    }

    private void openPropertyCategory(int imageResId, String subCategoryName) {
        ImageView imageView = PropertyFragment.this.requireView().findViewById(imageResId);
        imageView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            PropertyFragmentDirections.ActionPropertyFragmentToBaseFragment navAction =
                    PropertyFragmentDirections.actionPropertyFragmentToBaseFragment();
            navAction.setArgsMarketCategoryName("Property");
            navAction.setArgsMarketSubCategoryName(subCategoryName);
            navController.navigate(navAction);
        });
    }
}