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

package com.tailoredapps.androidapptemplate.base.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.squareup.leakcanary.RefWatcher
import com.tailoredapps.androidutil.viewstate.VS
import com.tailoredapps.androidutil.viewstate.ViewState
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject

abstract class BaseActivity(
    @LayoutRes protected val layout: Int
) : AppCompatActivity(), ViewState by VS() {
    private val refWatcher: RefWatcher by inject()

    open val disposables = CompositeDisposable()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreStateFrom(savedInstanceState)
        setContentView(layout)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        storeStateIn(outState)
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
        refWatcher.watch(this)
    }
}
