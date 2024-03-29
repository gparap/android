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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScannerViewModel : ViewModel() {
    //barcode scan result text
    private val barcodeScanResultTextLivedata = MutableLiveData<String>()
    val barcodeScanResultText: LiveData<String> = barcodeScanResultTextLivedata
    fun setBarcodeText(barcodeText: String){
        barcodeScanResultTextLivedata.value = barcodeText
    }

    //barcode scan result image
    private val barcodeScanResultImageLivedata = MutableLiveData<String>()
    val barcodeScanResultImage: LiveData<String> = barcodeScanResultImageLivedata
    fun setBarcodeImage(barcodeImage: String){
        barcodeScanResultImageLivedata.value = barcodeImage
    }
}