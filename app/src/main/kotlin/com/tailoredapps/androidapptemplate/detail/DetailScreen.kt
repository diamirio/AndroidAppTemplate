/*
 * Copyright 2019 Florian Schuster.
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
import at.florianschuster.reaktor.ReactorView
import at.florianschuster.reaktor.android.koin.reactor
import com.tailoredapps.androidapptemplate.R
import com.tailoredapps.androidapptemplate.core.DataRepo
import com.tailoredapps.androidapptemplate.core.model.Model
import com.tailoredapps.androidapptemplate.base.ui.BaseFragment
import com.tailoredapps.androidapptemplate.base.ui.BaseReactor

class DetailFragment : BaseFragment(R.layout.fragment_detail), ReactorView<DetailReactor> {
    override val reactor: DetailReactor by reactor()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(reactor)
    }

    override fun bind(reactor: DetailReactor) {
        // bind view

        // bind state

        // bind actions
    }
}

class DetailReactor(
    private val dataRepo: DataRepo
) : BaseReactor<DetailReactor.Action, DetailReactor.Mutation, DetailReactor.State>(State()) {
    object Action

    object Mutation

    data class State(
        val model: Model = Model()
    )
}
