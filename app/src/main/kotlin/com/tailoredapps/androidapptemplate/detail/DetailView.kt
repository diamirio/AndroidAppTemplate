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
import androidx.navigation.fragment.findNavController
import coil.load
import com.tailoredapps.androidapptemplate.R
import com.tailoredapps.androidapptemplate.base.ui.launchWhenStartedCancelWhenStopped
import com.tailoredapps.androidapptemplate.base.ui.viewBinding
import com.tailoredapps.androidapptemplate.core.DataRepo
import com.tailoredapps.androidapptemplate.databinding.FragmentDetailBinding
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailView(
    /**
     * This is just to show that injection in fragments work.
     * Please do not use a repo or service in the view.
     */
    private val dataRepo: DataRepo,
) : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val navController by lazy(::findNavController)
    private val viewModel by viewModel<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchWhenStartedCancelWhenStopped {
            // bind viewModel.state via viewModel.state
            viewModel.state.map { it.logoUrl }
                .distinctUntilChanged()
                .onEach { url ->
                    binding.ivLogo.load(url) { crossfade(durationMillis = 1000) }
                }
                .launchIn(scope = this)

            // bind actions via viewModel.dispatch
        }
    }
}
