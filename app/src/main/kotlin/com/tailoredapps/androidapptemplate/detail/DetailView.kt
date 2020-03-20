/*
 * Copyright 2020 Tailored Media GmbH.
 * Created by Florian Schuster.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tailoredapps.androidapptemplate.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import at.florianschuster.control.bind
import at.florianschuster.control.distinctMap
import coil.api.load
import com.tailoredapps.androidapptemplate.R
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.flow.launchIn
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailView : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModel()
    private val navController: NavController by lazy(::findNavController)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bind state via viewModel.state
        viewModel.state.distinctMap(DetailViewModel.State::logoUrl)
            .bind { url ->
                ivLogo.load(url) { crossfade(durationMillis = 1000) }
            }
            .launchIn(scope = viewLifecycleOwner.lifecycleScope)

        // bind actions via viewModel.dispatch
    }
}
