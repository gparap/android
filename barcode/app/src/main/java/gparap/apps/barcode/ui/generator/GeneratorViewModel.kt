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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GeneratorViewModel : ViewModel() {
    //generated barcode text
    private val barcodeTextLivedata = MutableLiveData<String>()
    val barcodeText: LiveData<String> = barcodeTextLivedata
    fun setBarcodeText(text: String) {
        barcodeTextLivedata.value = text
    }

    //generated barcode image
    private val barcodeImageLivedata = MutableLiveData<Bitmap>()
    val barcodeImage: LiveData<Bitmap> = barcodeImageLivedata
    fun getbarcodeImage() : Bitmap? {
        return barcodeImageLivedata.value
    }fun setBarcodeImage(image: Bitmap) {
        barcodeImageLivedata.value = image
    }

    //generated barcode save button
    private val saveButtonLivedata = MutableLiveData<Int>()
    val saveButton: LiveData<Int> = saveButtonLivedata
    fun setSaveButtonVisibility(visibility: Int) {
        saveButtonLivedata.value = visibility
    }
}