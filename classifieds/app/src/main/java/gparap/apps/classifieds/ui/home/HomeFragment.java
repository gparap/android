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
package gparap.apps.classifieds.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gparap.apps.classifieds.adapters.ClassifiedsAdapter;
import gparap.apps.classifieds.databinding.FragmentHomeBinding;
import gparap.apps.classifieds.models.ClassifiedModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //create a test list of classifieds
        ArrayList<ClassifiedModel> classifieds = new ArrayList<>();
        ClassifiedModel testClassified = new ClassifiedModel("", "test desc #1");
        testClassified.setPrice("999"); testClassified.setContactInfo("contact info 1");
        classifieds.add(testClassified);
        classifieds.add(new ClassifiedModel("", "test desc #2"));
        classifieds.add(new ClassifiedModel("", "test desc #3"));
        classifieds.add(new ClassifiedModel("", "test desc #4"));

        //create the adapter for the feed of classifieds
        ClassifiedsAdapter adapter = new ClassifiedsAdapter();
        adapter.setClassifieds(classifieds);

        //create the RecyclerView of the classifieds and set its adapter
        final RecyclerView recyclerView = binding.recyclerViewHomeFeed;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getBaseContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}