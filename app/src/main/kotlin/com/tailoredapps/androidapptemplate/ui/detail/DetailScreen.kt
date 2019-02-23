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

package com.tailoredapps.androidapptemplate.ui.detail

import com.tailoredapps.androidapptemplate.R
import com.tailoredapps.androidapptemplate.ui.base.reactor.BaseReactor
import com.tailoredapps.androidapptemplate.ui.base.reactor.ReactorFragment
import com.tailoredapps.androidapptemplate.ui.base.reactor.reactor
import com.tailoredapps.core.DataRepo
import com.tailoredapps.core.model.Model


class DetailFragment : ReactorFragment<DetailReactor>(R.layout.fragment_detail) {
    override val reactor: DetailReactor by reactor()

    override fun bind(reactor: DetailReactor) {
        //bind actions and state
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
