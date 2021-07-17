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
package gparap.apps.hangman

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    //alphabet letters
    private var letterLiveData: MutableLiveData<ArrayList<TextView>> = MutableLiveData()
    fun getLetters(): MutableLiveData<ArrayList<TextView>> {
        return letterLiveData
    }
    fun setLetters(letters: ArrayList<TextView>) {
        letterLiveData.value = letters
    }

    //word to find
    private var wordLiveData: MutableLiveData<String> = MutableLiveData()
    fun getWordToFind(): MutableLiveData<String> {
        return wordLiveData
    }
    fun setWordToFind(word: String) {
        wordLiveData.value = word
    }
}