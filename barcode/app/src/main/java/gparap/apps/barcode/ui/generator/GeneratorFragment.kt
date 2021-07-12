/*
 * Copyright 2021 gparap
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
package gparap.apps.barcode.ui.generator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import gparap.apps.barcode.databinding.FragmentGeneratorBinding

class GeneratorFragment : Fragment() {
    private lateinit var generatorViewModel: GeneratorViewModel
    private var fragmentGeneratorBinding: FragmentGeneratorBinding? = null
    private val binding get() = fragmentGeneratorBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //create (or return existing) ViewModel for this fragment
        generatorViewModel = ViewModelProvider(this).get(GeneratorViewModel::class.java)

        //return the root View associated with this Fragment layout
        fragmentGeneratorBinding = FragmentGeneratorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //observe text for generating a barcode
        val editTextGenerateBarcode: EditText = binding.editTextGenerateBarcode
        generatorViewModel.barcodeText.observe(viewLifecycleOwner, {
            editTextGenerateBarcode.setText(it)
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentGeneratorBinding = null
    }
}