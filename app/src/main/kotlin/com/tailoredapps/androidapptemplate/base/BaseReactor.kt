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

package com.tailoredapps.androidapptemplate.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleOwner
import at.florianschuster.reaktor.android.ViewModelReactor
import com.squareup.leakcanary.RefWatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.definition.Definition
import org.koin.core.inject
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

abstract class BaseReactor<Action : Any, Mutation : Any, State : Any>(
    initialState: State
) : ViewModelReactor<Action, Mutation, State>(initialState), KoinComponent {
    private val refWatcher: RefWatcher by inject()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        refWatcher.watch(this)
    }
}

/**
 * Reactor DSL extension to declare a Reactor in a Koin Module.
 */
inline fun <reified Reactor : BaseReactor<*, *, *>> Module.reactor(
    qualifier: Qualifier? = null,
    override: Boolean = false,
    noinline definition: Definition<Reactor>
): Unit = viewModel(qualifier, override, definition)

/**
 * Lazily gets a reactor instance for a LifecycleOwner.
 */
inline fun <L : LifecycleOwner, reified Reactor : BaseReactor<*, *, *>> L.reactor(
    qualifier: Qualifier? = null,
    scope: Scope = Scope.GLOBAL,
    noinline parameters: ParametersDefinition? = null
): Lazy<Reactor> = viewModel(qualifier, scope, parameters)
