/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.multiplex_clock.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import gparap.apps.multiplex_clock.R;

public class ChronometerFragment extends Fragment {
    private Chronometer chronometer;
    private Button buttonStart;
    private Button buttonStop;
    private Button buttonReset;

    public ChronometerFragment() {
    }

    public static ChronometerFragment newInstance() {
        ChronometerFragment fragment = new ChronometerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chronometer, container, false);
        getFragmentWidgets(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        addOnClickListenersToFragmentWidgets();
    }

    private void startTimer() {
        System.out.println("Chronometer started");
    }

    private void stopTimer() {
        System.out.println("Chronometer stopped");
    }

    private void resetTimer() {
        System.out.println("Chronometer reset");
    }

    private void addOnClickListenersToFragmentWidgets() {
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void getFragmentWidgets(View view) {
        chronometer = view.findViewById(R.id.chronometer);
        buttonStart = view.findViewById(R.id.buttonStart);
        buttonStop = view.findViewById(R.id.buttonStop);
        buttonReset = view.findViewById(R.id.buttonReset);
    }
}