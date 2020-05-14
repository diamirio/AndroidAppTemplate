package com.tailoredapps.androidapptemplate.base.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Launches [block] once the [LifecycleOwner.getLifecycle] reaches state
 * [Lifecycle.Event.ON_START]. The [CoroutineScope] provided in [block] is
 * canceled when the state reaches [Lifecycle.Event.ON_STOP].
 *
 * The [CoroutineScope.coroutineContext] provided in [block], consists of a
 * [SupervisorJob] and [Dispatchers.Main.immediate].
 *
 *
 * Example:
 *
 * ```
 * class MainActivity : Activity(R.layout.activity_main) {
 *
 *     init {
 *         lifecycle.launchWhenStartedCancelWhenStopped {
 *             emptyFlow().launchIn(this)
 *             launch { suspendingFunction() }
 *         }
 *     }
 * }
 * ```
 */
fun Lifecycle.launchWhenStartedCancelWhenStopped(
    block: suspend CoroutineScope.() -> Unit
) {
    var scope: CoroutineScope? = null
    addObserver(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_STOP) {
            scope?.cancel()
            scope = null
        } else if (event == Lifecycle.Event.ON_START) {
            scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
            scope?.launch(block = block)
        }
    })
}

/**
 * Waits for the [Fragment.getViewLifecycleOwner] to be present, then executes
 * [Lifecycle.launchWhenStartedCancelWhenStopped] on it.
 *
 *
 * Example:
 *
 * ```
 * class SomeFragment : Fragment(R.layout.fragment_some) {
 *
 *     init {
 *         launchWhenViewStartedCancelWhenViewStopped {
 *             emptyFlow().launchIn(this)
 *             launch { suspendingFunction() }
 *         }
 *     }
 * }
 * ```
 */
fun <F : Fragment> F.launchWhenViewStartedCancelWhenViewStopped(
    block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwnerLiveData.observe(this) { viewLifecycleOwner ->
        viewLifecycleOwner.lifecycle.launchWhenStartedCancelWhenStopped(block)
    }
}