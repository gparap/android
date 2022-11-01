/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.paidagogaki_gr.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gparap.apps.paidagogaki_gr.data.PostModel

class MainActivityViewModel : ViewModel() {
    private var postsLiveData: MutableLiveData<MutableList<PostModel>> = MutableLiveData()

    fun getPosts(): MutableLiveData<MutableList<PostModel>> {
        return postsLiveData
    }

    fun setPosts(posts: MutableList<PostModel>) {
        postsLiveData.value = posts
    }
}