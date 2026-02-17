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
package gparap.apps.classifieds.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import gparap.apps.classifieds.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DetailsViewModel detailsViewModel =
                new ViewModelProvider(this).get(DetailsViewModel.class);

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //get fragment data
        DetailsFragmentArgs fragmentArgs = DetailsFragmentArgs.fromBundle(getArguments());

        //get fragment views
        TextView classifiedDescription = binding.textViewClassifiedDetailsDescription;
        TextView classifiedPrice = binding.textViewClassifiedDetailsPrice;
        TextView classifiedContact = binding.textViewClassifiedDetailsContact;

        //display classified details
        classifiedDescription.setText(fragmentArgs.getArgsClassifiedDescription());
        classifiedPrice.setText(fragmentArgs.getArgsClassifiedPrice());
        classifiedContact.setText(fragmentArgs.getArgsClassifiedContact());

        //observe classified details
        detailsViewModel.getClassifiedDescription().observe(getViewLifecycleOwner(), classifiedDescription::setText);
        detailsViewModel.getClassifiedPrice().observe(getViewLifecycleOwner(), classifiedPrice::setText);
        detailsViewModel.getClassifiedContact().observe(getViewLifecycleOwner(), classifiedContact::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}