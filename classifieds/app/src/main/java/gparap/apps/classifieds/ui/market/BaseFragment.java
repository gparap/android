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

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gparap.apps.classifieds.R;
import gparap.apps.classifieds.adapters.ClassifiedsAdapter;
import gparap.apps.classifieds.models.ClassifiedModel;

public class BaseFragment extends Fragment {
    ArrayList<ClassifiedModel> classifiedsMarket;
    private BaseViewModel mViewModel;

    public static BaseFragment newInstance() {
        return new BaseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //create a test list of classifieds with generic category & sub-category
        classifiedsMarket = new ArrayList<>();
        ClassifiedModel testClassified = new ClassifiedModel("", "short desc #1");
        testClassified.setPrice("111"); testClassified.setContactInfo("contact info 1");
        classifiedsMarket.add(testClassified);
        classifiedsMarket.add(new ClassifiedModel("", " desc #2"));
        classifiedsMarket.add(new ClassifiedModel("", " desc #3"));
        classifiedsMarket.add(new ClassifiedModel("", " desc #4"));

        return inflater.inflate(R.layout.fragment_market_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //get fragment arguments
        BaseFragmentArgs args = BaseFragmentArgs.fromBundle(getArguments());
        String  marketCategoryName = args.getArgsMarketCategoryName();
        String  marketSubCategoryName = args.getArgsMarketSubCategoryName();

        //create adapter and add classifieds based on category & sub-category
        //!!! using the test list
        ClassifiedsAdapter classifiedsAdapter = new ClassifiedsAdapter();
        switch (marketCategoryName) {
            case "Animals":
                switch (marketSubCategoryName) {
                    case "Birds":
                    case "Dogs":
                    case "Cats":
                    case "Fish":
                    case "Food":
                    case "Reptiles": classifiedsAdapter.setClassifieds(classifiedsMarket);
                    break;
                }
                break;

            case "Automobiles":
                switch (marketSubCategoryName) {
                    case "Accessories":
                    case "Bicycles":
                    case "Bikes":
                    case "Cars":
                    case "Parts":
                    case "Utility": classifiedsAdapter.setClassifieds(classifiedsMarket);
                        break;
                }
                break;

            case "Clothing":
                switch (marketSubCategoryName) {
                    case "Accessories":
                    case "Kids":
                    case "Men":
                    case "Shoes":
                    case "Sportswear":
                    case "Women": classifiedsAdapter.setClassifieds(classifiedsMarket);
                        break;
                }
                break;

            case "Electronics":
                switch (marketSubCategoryName) {
                    case "Audio":
                    case "Cameras":
                    case "Computers":
                    case "Gadgets":
                    case "Mobiles":
                    case "TVs": classifiedsAdapter.setClassifieds(classifiedsMarket);
                        break;
                }
                break;

            case "Employment":
                switch (marketSubCategoryName) {
                    case "Freelance":
                    case "FullTime":
                    case "Internship":
                    case "Other":
                    case "PartTime":
                    case "Remote": classifiedsAdapter.setClassifieds(classifiedsMarket);
                        break;
                }
                break;

            case "Home":
                switch (marketSubCategoryName) {
                    case "Appliances":
                    case "Decor":
                    case "Furniture":
                    case "Garden":
                    case "Kitchen":
                    case "Other": classifiedsAdapter.setClassifieds(classifiedsMarket);
                        break;
                }
                break;

            case "Property":
                switch (marketSubCategoryName) {
                    case "Commercial":
                    case "Guest":
                    case "Land":
                    case "Rent":
                    case "Sale":
                    case "Other": classifiedsAdapter.setClassifieds(classifiedsMarket);
                        break;
                }
                break;

            case "Services":
                switch (marketSubCategoryName) {
                    case "Beauty":
                    case "Cleaning":
                    case "Events":
                    case "Other":
                    case "Repair":
                    case "Tutoring": classifiedsAdapter.setClassifieds(classifiedsMarket);
                        break;
                }
                break;

            case "Sports":
                switch (marketSubCategoryName) {
                    case "Apparel":
                    case "Equipment":
                    case "Fitness":
                    case "Indoors":
                    case "Other":
                    case "Outdoors": classifiedsAdapter.setClassifieds(classifiedsMarket);
                        break;
                }
                break;
        }

        //setup recycler view with adapter
        RecyclerView recyclerViewMarket = view.findViewById(R.id.recycler_view_market);
        recyclerViewMarket.setLayoutManager(new LinearLayoutManager(requireActivity().getBaseContext()));
        recyclerViewMarket.setAdapter(classifiedsAdapter);
    }
}