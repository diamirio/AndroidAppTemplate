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

package com.tailoredapps.androidapptemplate

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import kotlin.coroutines.ContinuationInterceptor

/**
 * Rule that is a [TestCoroutineScope].
 *
 * Coroutine's launched in [TestCoroutineScopeRule] are auto canceled via
 * [TestCoroutineScope.cleanupTestCoroutines] after the test completes.
 *
 * @property overrideMainDispatcher Boolean if set to true, [Dispatchers.Main] will use
 * [TestCoroutineDispatcher] of [TestCoroutineScope].
 */
@ExperimentalCoroutinesApi
class TestCoroutineScopeRule(
    val overrideMainDispatcher: Boolean = false
) : TestRule, TestCoroutineScope by TestCoroutineScope() {

    val testDispatcher = coroutineContext[ContinuationInterceptor] as TestCoroutineDispatcher

    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                if (overrideMainDispatcher) Dispatchers.setMain(testDispatcher)
                base.evaluate()
                cleanupTestCoroutines()
                if (overrideMainDispatcher) Dispatchers.resetMain()
            }
        }
}
