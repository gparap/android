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
package gparap.apps.barcode.ui.scanner

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.integration.android.IntentIntegrator
import gparap.apps.barcode.R
import gparap.apps.barcode.databinding.FragmentScannerBinding

class ScannerFragment : Fragment() {
    private lateinit var scannerViewModel: ScannerViewModel
    private var fragmentScannerBinding: FragmentScannerBinding? = null
    private val binding get() = fragmentScannerBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //create (or return existing) ViewModel for this fragment
        scannerViewModel = ViewModelProvider(this).get(ScannerViewModel::class.java)

        //return the root View associated with this Fragment layout
        fragmentScannerBinding = FragmentScannerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //observe barcode scan result text
        val textViewScanResult: TextView = binding.textViewScanResult
        scannerViewModel.barcodeScanResultText.observe(viewLifecycleOwner, {
            textViewScanResult.text = it
        })

        //observe barcode scan result image
        val imageViewScanResult = binding.imageViewScanResult
        scannerViewModel.barcodeScanResultImage.observe(viewLifecycleOwner, {
            imageViewScanResult.background = Drawable.createFromPath(it)
        })

        //scan for barcodes
        val buttonScanBarcode: Button = binding.buttonScanBarcode
        buttonScanBarcode.setOnClickListener {
            startBarcodeScanner()
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //get the result of the scan
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (intentResult != null && intentResult.contents != null) {
            //display the raw content of barcode
            scannerViewModel.setBarcodeText(intentResult.contents)

            //display the barcode image
            scannerViewModel.setBarcodeImage(intentResult.barcodeImagePath)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentScannerBinding = null
    }

    private fun startBarcodeScanner() {
        //create an integrator for the scanning intent
        val intentIntegrator = IntentIntegrator.forSupportFragment(this)

        //set scanning options
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        intentIntegrator.setPrompt(getString(R.string.text_scan_barcode))
        intentIntegrator.setBeepEnabled(true)
        intentIntegrator.setBarcodeImageEnabled(true)
        intentIntegrator.setOrientationLocked(false)

        //start scanning
        intentIntegrator.initiateScan()
    }
}