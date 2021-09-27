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

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import gparap.apps.barcode.R
import gparap.apps.barcode.databinding.FragmentGeneratorBinding
import gparap.apps.barcode.utils.Utils

class GeneratorFragment : Fragment() {
    private lateinit var generatorViewModel: GeneratorViewModel
    private var fragmentGeneratorBinding: FragmentGeneratorBinding? = null
    private val binding get() = fragmentGeneratorBinding!!
    private lateinit var editTextGenerateBarcode: EditText
    private var bitmap: Bitmap? = null

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

        //observe generated barcode text
        editTextGenerateBarcode = binding.editTextGenerateBarcode
        generatorViewModel.barcodeText.observe(viewLifecycleOwner, {
            editTextGenerateBarcode.setText(it)
        })

        //observe generated barcode image
        val barcodeImage: ImageView = binding.imageViewGeneratedBarcode
        generatorViewModel.barcodeImage.observe(viewLifecycleOwner, {
            barcodeImage.setImageBitmap(it)
        })

        //generate barcode
        val buttonSaveGenerateBarcode = binding.buttonSaveGenerateBarcode
        val buttonGenerateBarcode: Button = binding.buttonGenerateBarcode
        buttonGenerateBarcode.setOnClickListener {
            val isGenerated = generateBarcode()

            //unhide save button
            if (isGenerated){
                buttonSaveGenerateBarcode?.visibility = View.VISIBLE
            }
        }

        //save generated barcode to device
        buttonSaveGenerateBarcode?.setOnClickListener {
            Utils.saveBarcode(
                context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath,
                editTextGenerateBarcode.text.toString(),
                bitmap!!
            )
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentGeneratorBinding = null
    }

    private fun generateBarcode() : Boolean {
        val barcodeEncoder = BarcodeEncoder()
        val isGenerated: Boolean

        //generate barcode from user content
        if (editTextGenerateBarcode.text.toString().isNotEmpty()) {
            bitmap = barcodeEncoder.encodeBitmap(
                editTextGenerateBarcode.text.toString(),
                BarcodeFormat.QR_CODE,
                1024, 1024  //set as default quality
            )

            //update barcode image
            if (bitmap != null){
                generatorViewModel.setBarcodeImage(bitmap!!)
            }

            //update ViewModel with barcode text
            generatorViewModel.setBarcodeText(editTextGenerateBarcode.text.toString())

            isGenerated = true
        }

        //display error message (empty user content to generate barcode)
        else {
            Toast.makeText(context, getString(R.string.toast_empty_text_generator), Toast.LENGTH_SHORT)
                .show()
            isGenerated = false
        }

        return isGenerated
    }

    private fun saveGeneratedBarcode() {
//        Utils.writeDataToFile(
//            Utils.createNewFile(
//                context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath,
//                editTextGenerateBarcode.text.toString()
//            ), Utils.getByteArray(bitmap!!)
//        )


    }
}