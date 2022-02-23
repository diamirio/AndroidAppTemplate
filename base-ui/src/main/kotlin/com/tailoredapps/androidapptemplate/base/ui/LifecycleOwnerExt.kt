package com.tailoredapps.androidapptemplate.base.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * Convenience function to for [repeatOnLifecycle] with [Lifecycle.State.STARTED]
 */
fun Fragment.repeatOnLifeCycleStart(scope: CoroutineScope.() -> Unit) {
    repeatOnLifeCycle(Lifecycle.State.STARTED, scope)
}

private fun Fragment.repeatOnLifeCycle(state: Lifecycle.State, scope: CoroutineScope.() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        this@repeatOnLifeCycle.repeatOnLifecycle(state) {
            scope(this)
        }
    }
}