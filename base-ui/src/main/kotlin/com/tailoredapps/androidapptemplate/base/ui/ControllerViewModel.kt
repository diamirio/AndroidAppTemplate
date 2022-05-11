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

package com.tailoredapps.androidapptemplate.base.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import at.florianschuster.control.Controller
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import leakcanary.AppWatcher

abstract class ControllerViewModel<Action, State> : ViewModel() {

    protected abstract val controller: Controller<Action, State>

    fun dispatch(action: Action) = controller.dispatch(action)
    val currentState: State get() = controller.state.value
    val state: StateFlow<State> get() = controller.state

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        AppWatcher.objectWatcher.expectWeaklyReachable(
            watchedObject = this,
            description = "${this::class.java.simpleName}.onCleared"
        )
    }
}
