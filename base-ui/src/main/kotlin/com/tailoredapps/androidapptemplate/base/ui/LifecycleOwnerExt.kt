package com.tailoredapps.androidapptemplate.base.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * Extension for [repeatOnLifecycle] with [Lifecycle.State.STARTED]
 */
fun Fragment.launchWhenStartedCancelWhenStopped(scope: CoroutineScope.() -> Unit) {
    repeatOnLifeCycle(Lifecycle.State.STARTED, scope)
}

/**
 * Extension for [repeatOnLifecycle] with [Lifecycle.State.CREATED]
 */
fun Fragment.launchWhenCreatedCancelWhenDestroyed(scope: CoroutineScope.() -> Unit) {
    repeatOnLifeCycle(Lifecycle.State.CREATED, scope)
}

/**
 * Extension for [repeatOnLifecycle] with [Lifecycle.State.RESUMED]
 */
fun Fragment.launchWhenResumedCancelWhenPaused(scope: CoroutineScope.() -> Unit) {
    repeatOnLifeCycle(Lifecycle.State.RESUMED, scope)
}

private fun Fragment.repeatOnLifeCycle(
    state: Lifecycle.State,
    scope: CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(state, scope)
    }
}


/**
 * Extension for [repeatOnLifecycle] with [Lifecycle.State.STARTED]
 */
fun AppCompatActivity.launchWhenStartedCancelWhenStopped(scope: CoroutineScope.() -> Unit) {
    repeatOnLifeCycle(Lifecycle.State.STARTED, scope)
}

/**
 * Extension for [repeatOnLifecycle] with [Lifecycle.State.CREATED]
 */
fun AppCompatActivity.launchWhenCreatedCancelWhenDestroyed(scope: CoroutineScope.() -> Unit) {
    repeatOnLifeCycle(Lifecycle.State.CREATED, scope)
}

/**
 * Extension for [repeatOnLifecycle] with [Lifecycle.State.RESUMED]
 */
fun AppCompatActivity.launchWhenResumedCancelWhenPaused(scope: CoroutineScope.() -> Unit) {
    repeatOnLifeCycle(Lifecycle.State.RESUMED, scope)
}

private fun AppCompatActivity.repeatOnLifeCycle(
    state: Lifecycle.State,
    scope: CoroutineScope.() -> Unit,
) {
    lifecycleScope.launch {
        repeatOnLifeCycle(state, scope)
    }
}