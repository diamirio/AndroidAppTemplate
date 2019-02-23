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

package com.tailoredapps.androidapptemplate.ui.base.reactor

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import at.florianschuster.androidreactor.Reactor
import at.florianschuster.androidreactor.ReactorView
import com.tailoredapps.androidapptemplate.ui.base.BaseFragment


abstract class ReactorFragment<R>(@LayoutRes layout: Int? = null) : BaseFragment(layout), ReactorView<R> where R : Reactor<*, *, *> {
    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind(reactor)
    }
}